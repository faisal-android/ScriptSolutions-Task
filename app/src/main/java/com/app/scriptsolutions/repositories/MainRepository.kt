package com.app.scriptsolutions.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.scriptsolutions.data.CityModel
import com.app.scriptsolutions.data.CountryModel
import com.app.scriptsolutions.data.DataUtil

class MainRepository {

    private var _countriesData = MutableLiveData<MutableList<CountryModel>>()
    private var _citiesData = MutableLiveData<MutableList<CityModel>>()

    val countriesData: LiveData<MutableList<CountryModel>>
        get() = _countriesData

    val selectedCountryCitiesData: LiveData<MutableList<CityModel>>
        get() = _citiesData


    init {
        _countriesData.value = DataUtil().prepareCountriesData()
        _citiesData.value= mutableListOf()
    }

    fun filterCountryCities(query: String, selectedCountryPosition: Int) {
        _citiesData.postValue(
            countriesData.value!![selectedCountryPosition].cities.filter {
                it.name.contains(query, true)
            }.toMutableList())
    }
}
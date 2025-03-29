package com.app.scriptsolutions.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.scriptsolutions.data.CityModel
import com.app.scriptsolutions.data.CountryModel
import com.app.scriptsolutions.data.DataUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainRepository {

    private var _countriesData = MutableLiveData<MutableList<CountryModel>>(mutableListOf())
    private var _citiesData = MutableLiveData<MutableList<CityModel>>(mutableListOf())

    val countriesData: LiveData<MutableList<CountryModel>>
        get() = _countriesData

    val selectedCountryCitiesData: LiveData<MutableList<CityModel>>
        get() = _citiesData

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val countriesData = DataUtil().prepareCountriesData()
            _countriesData.postValue(countriesData)
        }
    }

    fun filterCountryCities(query: String, selectedCountryPosition: Int) {
        _citiesData.postValue(
            countriesData.value!![selectedCountryPosition].cities.filter {
                it.name.contains(query, true)
            }.toMutableList()
        )
    }
}
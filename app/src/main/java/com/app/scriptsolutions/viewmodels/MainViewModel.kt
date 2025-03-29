package com.app.scriptsolutions.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.scriptsolutions.repositories.MainRepository

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    val countriesData get() = mainRepository.countriesData
    val selectedCountryCitiesData get() = mainRepository.selectedCountryCitiesData
    var selectedCountryPosition = MutableLiveData(0)

    fun filterCountryCities(query: String) {
        mainRepository.filterCountryCities(query, selectedCountryPosition.value!!)
    }

}
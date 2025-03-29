package com.app.scriptsolutions.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.scriptsolutions.repositories.MainRepository

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    val countriesData get() = mainRepository.countriesData
    val selectedCountryCitiesData get() = mainRepository.selectedCountryCitiesData
    var selectedCountryPosition = MutableLiveData(-1)

    fun filterCountryCities(query: String) {
        if (selectedCountryPosition.value!! > -1)
            mainRepository.filterCountryCities(query, selectedCountryPosition.value!!)
    }
}
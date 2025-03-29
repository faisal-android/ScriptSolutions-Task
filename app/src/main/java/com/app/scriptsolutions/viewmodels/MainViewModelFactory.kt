package com.app.scriptsolutions.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.scriptsolutions.repositories.MainRepository

class MainViewModelFactory(var  mainRepository: MainRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(mainRepository) as T
    }
}
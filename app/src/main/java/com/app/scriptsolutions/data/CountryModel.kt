package com.app.scriptsolutions.data

data class CountryModel(
    val name: String,
    val imagePath: Int,
    val isSelected: Boolean,
    val cities: MutableList<CityModel> = mutableListOf()
)

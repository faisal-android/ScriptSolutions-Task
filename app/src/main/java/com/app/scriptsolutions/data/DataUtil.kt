package com.app.scriptsolutions.data

import com.app.scriptsolutions.R

class DataUtil {

    private val countriesList =
        mutableListOf("Bahrain", "Saudi Arabia", "Qatar", "Oman", "UAE", "Kuwait")
    private val countriesImagesList =
        mutableListOf(
            R.drawable.img_bahrain, R.drawable.img_saudi_arabia, R.drawable.img_qatar,
            R.drawable.img_oman, R.drawable.img_uae, R.drawable.img_kuwait
        )
    private val bahrainCitiesList =
        mutableListOf(
            "Manama",
            "Hamad Town",
            "Muharraq",
            "Isa Town",
            "Riffa",
            "Sitra",
            "Jidhafs",
            "Budaiya",
            "Diraz",
            "Jid Ali",
            "Sanabis",
            "Tubli",
            "Durrat Al Bahrain",
            "Gudaibiya",
        )
    private val saudiCitiesList =
        mutableListOf(
            "Mecca",
            "Medina",
            "Najran",
            "Abha",
            "Ad-Dilam",
            "Al-Abwa",
            "Badr",
            "Buraydah",
            "Arar",
            "Dhahran",
            "Dammam",
            "Dhahran",
            "Jizan",
        )
    private val qatarCitiesList =
        mutableListOf(
            "Al Wakrah",
            "Old Airport",
            "Al-Shahaniya",
            "Mesaieed",
            "Najma",
            "Mushayrib",
            "Rawdat Al Khail"
        )
    private val omanCitiesList =
        mutableListOf(
            "Adam",
            "As Sib",
            "Al Ashkharah",
            "Duqm",
            "Khasab",
            "Samail",
            "Sur",
            "Salalah",
            "Jalan Bani Bu Hassan"
        )
    private val uaeCitiesList =
        mutableListOf(
            "Dubai",
            "Abu Dhabi",
            "Sharjah",
            "Al Ain",
            "Ajman",
            "Ras Al Khaimah",
            "Fujairah",
            "Umm Al Quwain"
        )
    private val kuwaitCitiesList =
        mutableListOf(
            "Kuwait Ciy",
            "Al Jahrā’",
            "Abū Ḩulayfah",
            "Al Aḩmadī",
            "Ar Riqqah",
            "Al Bida‘",
            "Mishrif"
        )

    fun prepareCountriesData(): MutableList<CountryModel> {
        val countryModelsList = mutableListOf<CountryModel>()
        countriesList.forEachIndexed { index, it ->
            countryModelsList.add(
                CountryModel(
                    it,
                    countriesImagesList[index],
                    true,
                    getCountryCitiesList(it)
                )
            )
        }
        return countryModelsList
    }

    private fun getCountryCitiesList(country: String): MutableList<CityModel> {
        val cityModelsList = mutableListOf<CityModel>()
        when (country) {
            "Bahrain" -> {
                bahrainCitiesList.forEach {
                    cityModelsList.add(CityModel(it, R.drawable.img_bahrain))
                }
            }

            "Saudi Arabia" -> {
                saudiCitiesList.forEach {
                    cityModelsList.add(CityModel(it, R.drawable.img_saudi_arabia))
                }
            }

            "Qatar" -> {
                qatarCitiesList.forEach {
                    cityModelsList.add(CityModel(it, R.drawable.img_qatar))
                }
            }

            "Oman" -> {
                omanCitiesList.forEach {
                    cityModelsList.add(CityModel(it, R.drawable.img_oman))
                }
            }

            "UAE" -> {
                uaeCitiesList.forEach {
                    cityModelsList.add(CityModel(it, R.drawable.img_uae))
                }
            }

            "Kuwait" -> {
                kuwaitCitiesList.forEach {
                    cityModelsList.add(CityModel(it, R.drawable.img_kuwait))
                }
            }
        }
        return cityModelsList
    }
}
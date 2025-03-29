package com.app.scriptsolutions.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.app.scriptsolutions.R
import com.app.scriptsolutions.adapters.CitiesListingAdapter
import com.app.scriptsolutions.adapters.CountriesSwiperAdapter
import com.app.scriptsolutions.databinding.ActivityMainBinding
import com.app.scriptsolutions.repositories.MainRepository
import com.app.scriptsolutions.viewmodels.MainViewModel
import com.app.scriptsolutions.viewmodels.MainViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainRepository: MainRepository
    private lateinit var dotsImage: Array<ImageView>
    private lateinit var countrySwiperAdapter: CountriesSwiperAdapter
    private lateinit var cityListingAdapter: CitiesListingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        prepareViewModel()
        prepareCountryAdapter()
        prepareCityAdapter()
        prepareDotIndicators()
        setListeners()
    }

    private fun prepareViewModel() {
        mainRepository = MainRepository()
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(mainRepository)
        ).get(MainViewModel::class.java)
    }

    private fun prepareCountryAdapter() {
        countrySwiperAdapter = CountriesSwiperAdapter()
        countrySwiperAdapter.submitCountriesData(mainViewModel.countriesData.value!!)
        binding.vpCountries.adapter = countrySwiperAdapter
    }

    private fun prepareCityAdapter() {
        cityListingAdapter = CitiesListingAdapter()
        cityListingAdapter.submitCitiesData(mainViewModel.selectedCountryCitiesData.value!!)
        binding.rvCities.layoutManager = LinearLayoutManager(this)
        binding.rvCities.adapter = cityListingAdapter
        binding.rvCities.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun prepareDotIndicators() {
        dotsImage = Array(mainViewModel.countriesData.value!!.size) {
            layoutInflater.inflate(
                R.layout.indicator_item,
                binding.llIndicators,
                false
            ) as ImageView
        }

        dotsImage.forEach {
            it.setImageResource(
                R.drawable.ic_inactive_indicator
            )
            binding.llIndicators.addView(it)
        }
    }

    private fun setListeners() {
        binding.vpCountries.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.svCities.setQuery("", false)
                mainViewModel.selectedCountryPosition.postValue(position)
                super.onPageSelected(position)
            }
        })

        mainViewModel.selectedCountryPosition.observe(this) {
            dotsImage.forEach {
                it.setImageResource(
                    R.drawable.ic_inactive_indicator
                )
            }
            dotsImage[it].setImageResource(R.drawable.ic_active_indicator)
            mainViewModel.filterCountryCities("")
        }

        mainViewModel.selectedCountryCitiesData.observe(this) {
            cityListingAdapter.submitCitiesData(it)
        }

        binding.svCities.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mainViewModel.filterCountryCities(newText!!)
                return false
            }

        })
    }
}
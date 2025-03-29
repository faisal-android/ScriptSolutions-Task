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

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var mainRepository: MainRepository
    private lateinit var indicatorImagesArray: Array<ImageView>
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

        prepareCountryAdapter()
        prepareCityAdapter()
        prepareViewModel()
        setListeners()
    }

    private fun prepareCountryAdapter() {
        countrySwiperAdapter = CountriesSwiperAdapter()
        binding.vpCountries.setOffscreenPageLimit(2)
        binding.vpCountries.adapter = countrySwiperAdapter
    }

    private fun prepareCityAdapter() {
        cityListingAdapter = CitiesListingAdapter()
        binding.rvCities.layoutManager = LinearLayoutManager(this)
        binding.rvCities.adapter = cityListingAdapter
        binding.rvCities.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    private fun prepareViewModel() {
        mainRepository = MainRepository()
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(mainRepository)
        ).get(MainViewModel::class.java)

        mainViewModel.countriesData.observe(this) {
            prepareDotIndicators()
            countrySwiperAdapter.submitCountriesData(it)
        }
        mainViewModel.selectedCountryCitiesData.observe(this) {
            cityListingAdapter.submitCitiesData(it)
        }

        mainViewModel.selectedCountryPosition.observe(this) {
            binding.svCities.setQuery("", false)
            queryChangeListener.onQueryTextChange("")
        }
    }

    private fun prepareDotIndicators() {
        binding.llIndicators.removeAllViews()
        indicatorImagesArray = Array(mainViewModel.countriesData.value!!.size) {
            layoutInflater.inflate(
                R.layout.indicator_item,
                binding.llIndicators,
                false
            ) as ImageView
        }

        indicatorImagesArray.forEach {
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
                mainViewModel.selectedCountryPosition.value = position
                indicatorImagesArray.forEach {
                    it.setImageResource(
                        R.drawable.ic_inactive_indicator
                    )
                }
                indicatorImagesArray[position].setImageResource(R.drawable.ic_active_indicator)
                super.onPageSelected(position)
            }
        })
        binding.svCities.setOnQueryTextListener(queryChangeListener)
    }

    private var queryChangeListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            mainViewModel.filterCountryCities(newText!!)
            return false
        }
    }
}
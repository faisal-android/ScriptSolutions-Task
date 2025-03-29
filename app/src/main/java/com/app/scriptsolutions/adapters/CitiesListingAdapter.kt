package com.app.scriptsolutions.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.scriptsolutions.data.CityModel
import com.app.scriptsolutions.databinding.CityListingItemBinding

class CitiesListingAdapter : RecyclerView.Adapter<CityViewHolder>() {

    private var citiesDataList = mutableListOf<CityModel>()

    fun submitCitiesData(citiesDataList: MutableList<CityModel>) {
        this.citiesDataList.clear()
        this.citiesDataList.addAll(citiesDataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = CityListingItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        binding.ivCityImage.clipToOutline=true
        return CityViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return citiesDataList.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.binding.ivCityImage.setImageResource(citiesDataList[position].imagePath)
        holder.binding.tvCityName.text=citiesDataList[position].name
    }

}
package com.app.scriptsolutions.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.scriptsolutions.data.CountryModel
import com.app.scriptsolutions.databinding.CountrySwiperItemBinding

class CountriesSwiperAdapter : RecyclerView.Adapter<CountryViewHolder>() {

    private var countriesDataList = mutableListOf<CountryModel>()

    fun submitCountriesData(countriesDataList: MutableList<CountryModel>) {
        this.countriesDataList.clear()
        this.countriesDataList.addAll(countriesDataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = CountrySwiperItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return  countriesDataList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.binding.ivCountryImage.setImageResource(countriesDataList[position].imagePath)
        holder.binding.tvCountryName.setText(countriesDataList[position].name)
    }

}
package com.raafat.domain.services.carCreation

import com.raafat.data.model.Brand
import com.raafat.data.model.FuelType
import com.raafat.data.model.Series

interface CarCreationService {

    suspend fun loadBrands(query: String?): List<Brand>

    fun loadSeries(brand: Brand, query: String?): List<Series>

    fun loadYears(series: Series, query: String?): List<Int>

    fun loadFuelTypes(series: Series, query: String?): List<FuelType>
}
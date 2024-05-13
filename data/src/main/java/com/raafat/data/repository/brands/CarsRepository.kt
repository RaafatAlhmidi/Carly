package com.raafat.data.repository.brands

import com.raafat.data.model.Brand
import com.raafat.data.model.FuelType
import com.raafat.data.model.Series
import kotlinx.coroutines.flow.Flow

interface CarsRepository {

    suspend fun getBrands(): List<Brand>

    suspend fun getFuelTypes(): List<FuelType>

    suspend fun getSeries(): List<Series>

    fun getSelectedCar(): Flow<Brand?>
}
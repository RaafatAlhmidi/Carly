package com.raafat.data.repository.brands

import com.raafat.data.model.Brand
import kotlinx.coroutines.flow.Flow

interface CarsRepository {

    fun getBrands(): Flow<List<Brand>>
}
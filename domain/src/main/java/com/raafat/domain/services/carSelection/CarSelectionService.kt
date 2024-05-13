package com.raafat.domain.services.carSelection

import com.raafat.data.model.Brand
import com.raafat.data.model.Car
import com.raafat.data.model.FuelType
import com.raafat.data.model.Series
import kotlinx.coroutines.flow.Flow

interface CarSelectionService {

    suspend fun createCar(brand: Brand, series: Series, year: Int, fuelType: FuelType): Boolean

    fun getAllCars(): Flow<List<Car>>

    suspend fun selectCar(car: Car)

    suspend fun deleteCar(car: Car)

    fun getSelectedCar(): Flow<Car?>
}
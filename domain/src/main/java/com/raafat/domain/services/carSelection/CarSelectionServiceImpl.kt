package com.raafat.domain.services.carSelection

import com.raafat.data.model.Brand
import com.raafat.data.model.Car
import com.raafat.data.model.FuelType
import com.raafat.data.model.Series
import com.raafat.data.repository.brands.CarsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CarSelectionServiceImpl @Inject constructor(
    private val carsRepository: CarsRepository
) : CarSelectionService {

    override suspend fun createCar(brand: Brand, series: Series, year: Int, fuelType: FuelType): Boolean {
        val selectedCar = carsRepository.getSelectedCarSync()
        if (selectedCar != null)
            carsRepository.updateCar(
                selectedCar.copy(
                    isSelected = false
                )
            )

        return carsRepository.createCar(
            Car(
                id = null,
                brand = brand,
                series = series,
                year = year,
                fuelType = fuelType,
                isSelected = true
            )
        ) >= 0
    }

    override fun getAllCars(): Flow<List<Car>> = carsRepository.getAllCars()

    override suspend fun selectCar(car: Car) {
        val selectedCar = carsRepository.getSelectedCarSync()
        if (selectedCar != null)
            carsRepository.updateCar(
                selectedCar.copy(
                    isSelected = false
                )
            )
        carsRepository.updateCar(
            car.copy(
                isSelected = true
            )
        )
    }

    override suspend fun deleteCar(car: Car) {
        carsRepository.deleteCar(car)
    }

    override fun getSelectedCar(): Flow<Car?> = carsRepository.getSelectedCar()

}
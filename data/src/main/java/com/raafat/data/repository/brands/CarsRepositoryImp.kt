package com.raafat.data.repository.brands

import com.raafat.data.database.dao.BrandsDao
import com.raafat.data.database.dao.CarsDao
import com.raafat.data.database.dao.FuelTypesDao
import com.raafat.data.database.dao.SeriesDao
import com.raafat.data.mappers.mapToEntity
import com.raafat.data.mappers.mapToModel
import com.raafat.data.model.Brand
import com.raafat.data.model.Car
import com.raafat.data.model.FuelType
import com.raafat.data.model.Series
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CarsRepositoryImp @Inject constructor(
    private val brandsDao: BrandsDao,
    private val fuelTypesDao: FuelTypesDao,
    private val seriesDao: SeriesDao,
    private val carsDao: CarsDao
) : CarsRepository {

    override suspend fun getBrands(): List<Brand> {
        return brandsDao.getAll().map { entity ->
            entity.mapToModel()
        }
    }

    override suspend fun getFuelTypes(): List<FuelType> {
        return fuelTypesDao.getAll().map { entity ->
            entity.mapToModel()
        }
    }

    override suspend fun getSeries(): List<Series> {
        return seriesDao.getAll().map { entity ->
            entity.mapToModel()
        }
    }

    override fun getSelectedCar(): Flow<Car?> {
        return carsDao.getSelectedCar().map {
            it?.mapToModel()
        }
    }

    override suspend fun getSelectedCarSync(): Car? {
        return carsDao.getSelectedCarSync()?.mapToModel()
    }

    override suspend fun createCar(car: Car): Long {
        return carsDao.insertCar(car.mapToEntity())
    }

    override suspend fun updateCar(car: Car) {
        carsDao.updateCar(car.mapToEntity())
    }

    override suspend fun deleteCar(car: Car) {
        carsDao.deleteCar(car.mapToEntity())
    }

    override fun getAllCars(): Flow<List<Car>> {
        return carsDao.getAll().map {
            it.map { entity ->
                entity.mapToModel()
            }
        }
    }
}
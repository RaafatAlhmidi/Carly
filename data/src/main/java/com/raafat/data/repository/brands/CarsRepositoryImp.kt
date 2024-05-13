package com.raafat.data.repository.brands

import com.raafat.data.database.dao.BrandsDao
import com.raafat.data.database.dao.FuelTypesDao
import com.raafat.data.database.dao.SeriesDao
import com.raafat.data.mappers.mapToModel
import com.raafat.data.model.Brand
import com.raafat.data.model.FuelType
import com.raafat.data.model.Series
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CarsRepositoryImp @Inject constructor(
    private val brandsDao: BrandsDao,
    private val fuelTypesDao: FuelTypesDao,
    private val seriesDao: SeriesDao
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

    override fun getSelectedCar(): Flow<Brand?> {
        TODO("Not yet implemented")
    }
}
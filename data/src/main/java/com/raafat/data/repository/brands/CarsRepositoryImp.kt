package com.raafat.data.repository.brands

import com.raafat.data.database.dao.BrandsDao
import com.raafat.data.mappers.mapToModel
import com.raafat.data.model.Brand
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CarsRepositoryImp @Inject constructor(
    private val brandsDao: BrandsDao
) : CarsRepository {

    override suspend fun getBrands(): List<Brand> {
        return brandsDao.getAll().map { entity ->
            entity.mapToModel()
        }
    }

    override fun getSelectedCar(): Flow<Brand?> {
        TODO("Not yet implemented")
    }
}
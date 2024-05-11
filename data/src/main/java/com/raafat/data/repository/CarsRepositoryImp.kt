package com.raafat.data.repository

import com.raafat.data.database.dao.BrandsDao
import com.raafat.data.di.ApplicationScope
import com.raafat.data.di.DefaultDispatcher
import com.raafat.data.model.Brand
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CarsRepositoryImp @Inject constructor(
    private val brandsDao: BrandsDao,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineScope,
): CarsRepository {

    override fun getBrands(): Flow<List<Brand>> {
        return brandsDao.getAll().map { it.map { entity ->
            Brand(entity.name)
        } }
    }
}
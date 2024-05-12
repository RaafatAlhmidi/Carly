package com.raafat.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.raafat.data.database.Constants
import com.raafat.data.database.entities.BrandEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CarsDao {

    @Query("SELECT * FROM ${Constants.BRAND_TABLE}")
    fun getAll(): Flow<List<BrandEntity>>


}
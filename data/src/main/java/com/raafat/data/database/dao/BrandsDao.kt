package com.raafat.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.raafat.data.database.Constants
import com.raafat.data.database.relations.BrandWithSeriesAndFeatureRelation
import kotlinx.coroutines.flow.Flow

@Dao
interface BrandsDao {
    @Query("SELECT * FROM ${Constants.BRAND_TABLE}")
    fun getAll(): Flow<List<BrandWithSeriesAndFeatureRelation>>
}
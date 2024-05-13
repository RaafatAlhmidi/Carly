package com.raafat.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.raafat.data.database.Constants
import com.raafat.data.database.relations.BrandWithSeriesAndFeatureRelation

@Dao
interface BrandsDao {
    @Query("SELECT * FROM ${Constants.BRAND_TABLE}")
    suspend fun getAll(): List<BrandWithSeriesAndFeatureRelation>
}
package com.raafat.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.raafat.data.database.Constants
import com.raafat.data.database.entities.BrandEntity

@Dao
interface BrandsDao {
    @Query("SELECT * FROM ${Constants.BRAND_TABLE}")
    fun getAll(): List<BrandEntity>
}
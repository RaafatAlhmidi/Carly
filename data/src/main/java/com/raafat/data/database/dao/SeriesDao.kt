package com.raafat.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.raafat.data.database.Constants
import com.raafat.data.database.entities.SeriesEntity


@Dao
interface SeriesDao {

    @Query("SELECT * FROM ${Constants.SERIES_TABLE}")
    fun getAll(): List<SeriesEntity>

    @Query("SELECT * FROM ${Constants.SERIES_TABLE} WHERE ${Constants.BRAND_NAME_COLUMN} = :brandName")
    fun getAllForBrand(brandName: String): List<SeriesEntity>

}
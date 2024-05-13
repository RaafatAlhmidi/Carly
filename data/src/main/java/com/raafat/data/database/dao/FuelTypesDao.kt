package com.raafat.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.raafat.data.database.Constants
import com.raafat.data.database.entities.FuelTypeEntity

@Dao
interface FuelTypesDao {

    @Query("SELECT * FROM ${Constants.FUEL_TYPE_TABLE}")
    fun getAll(): List<FuelTypeEntity>

}
package com.raafat.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raafat.data.database.Constants

@Entity(tableName = Constants.FUEL_TYPE_TABLE)
data class FuelTypeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.FUEL_ID_COLUMN)
    val id: Int,
    @ColumnInfo(name = Constants.FUEL_TYPE_NAME_COLUMN)
    val name: String
)
package com.raafat.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raafat.data.database.Constants

@Entity(tableName = Constants.CAR_TABLE)
data class CarEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.CAR_ID_COLUMN)
    val id: Int?,

    @ColumnInfo(name = Constants.BRAND_NAME_COLUMN)
    val brandName: String,

    @ColumnInfo(name = Constants.SERIES_NAME_COLUMN)
    val seriesName: String,

    @ColumnInfo(name = Constants.CAR_YEAR_COLUMN)
    val year: String,

    @ColumnInfo(name = Constants.FUEL_TYPE_NAME_COLUMN)
    val fuelType: String
)
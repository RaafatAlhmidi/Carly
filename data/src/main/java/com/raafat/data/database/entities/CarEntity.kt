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

    @ColumnInfo(name = Constants.BRAND_ID_COLUMN)
    val brandId: Int,

    @ColumnInfo(name = Constants.SERIES_ID_COLUMN)
    val seriesId: Int,

    @ColumnInfo(name = Constants.CAR_YEAR_COLUMN)
    val year: Int,

    @ColumnInfo(name = Constants.FUEL_ID_COLUMN)
    val fuelType: Int,

    @ColumnInfo(name = Constants.CAR_IS_SELECTED_COLUMN)
    val isSelected: Boolean
)
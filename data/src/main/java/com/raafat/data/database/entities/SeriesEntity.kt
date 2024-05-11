package com.raafat.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raafat.data.database.Constants

@Entity(tableName = Constants.SERIES_TABLE)
data class SeriesEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.SERIES_ID_COLUMN)
    val id: Int,
    @ColumnInfo(name = Constants.SERIES_NAME_COLUMN)
    val name: String,
    @ColumnInfo(name = Constants.SERIES_MINIMUM_YEAR_COLUMN)
    val minimumYear: Int,
    @ColumnInfo(name = Constants.SERIES_MAXIMUM_YEAR_COLUMN)
    val maximumYear: Int,

    @ColumnInfo(name = Constants.BRAND_NAME_COLUMN)
    val brandName:Int
)

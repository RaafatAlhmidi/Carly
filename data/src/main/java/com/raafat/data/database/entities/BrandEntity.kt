package com.raafat.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raafat.data.database.Constants

@Entity(tableName = Constants.BRAND_TABLE)
data class BrandEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.BRAND_ID_COLUMN)
    val id: Int,

    @ColumnInfo(name = Constants.BRAND_NAME_COLUMN)
    val name: String
)
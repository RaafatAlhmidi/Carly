package com.raafat.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raafat.data.database.Constants

@Entity(tableName = Constants.FEATURE_TABLE)
data class FeatureEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.FEATURE_ID_COLUMN)
    val id: Int,
    @ColumnInfo(name = Constants.FEATURE_NAME_COLUMN)
    val name: String
)

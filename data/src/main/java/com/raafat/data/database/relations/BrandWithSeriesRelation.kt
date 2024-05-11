package com.raafat.data.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.raafat.data.database.Constants
import com.raafat.data.database.entities.BrandEntity
import com.raafat.data.database.entities.SeriesEntity


data class BrandWithSeriesRelation(
    @Embedded val brand: BrandEntity,
    @Relation(
        parentColumn = Constants.BRAND_NAME_COLUMN,
        entityColumn = Constants.BRAND_NAME_COLUMN
    )
    val series: List<SeriesEntity>
)
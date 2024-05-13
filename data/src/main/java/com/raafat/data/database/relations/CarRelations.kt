package com.raafat.data.database.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.raafat.data.database.Constants
import com.raafat.data.database.entities.BrandEntity
import com.raafat.data.database.entities.CarEntity
import com.raafat.data.database.entities.FuelTypeEntity
import com.raafat.data.database.entities.SeriesEntity

data class CarRelations(
    @Embedded
    val car: CarEntity,

    @Relation(
        entity = SeriesEntity::class,
        parentColumn = Constants.SERIES_ID_COLUMN,
        entityColumn = Constants.SERIES_ID_COLUMN
    )
    val series: SeriesWithFeaturesAndFuelsRelation,

    @Relation(
        parentColumn = Constants.FUEL_ID_COLUMN,
        entityColumn = Constants.FUEL_ID_COLUMN
    )
    val fuel: FuelTypeEntity,

    @Relation(
        parentColumn = Constants.BRAND_ID_COLUMN,
        entityColumn = Constants.BRAND_ID_COLUMN
    )
    val brand: BrandEntity,
) {
}
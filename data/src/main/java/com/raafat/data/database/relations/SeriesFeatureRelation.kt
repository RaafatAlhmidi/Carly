package com.raafat.data.database.relations

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.raafat.data.database.Constants
import com.raafat.data.database.entities.BrandEntity
import com.raafat.data.database.entities.FeatureEntity
import com.raafat.data.database.entities.FuelTypeEntity
import com.raafat.data.database.entities.SeriesEntity


@Entity(
    tableName = Constants.SERIES_AND_FEATURE_CROSS_REF_TABLE,
    primaryKeys = [Constants.SERIES_ID_COLUMN, Constants.FEATURE_ID_COLUMN]
)
data class SeriesAndFeatureCrossRef(
    @ColumnInfo(name = Constants.SERIES_ID_COLUMN)
    val seriesId: Int,
    @ColumnInfo(name = Constants.FEATURE_ID_COLUMN)
    val featureId: Int
)

@Entity(
    tableName = Constants.SERIES_AND_FUEL_CROSS_REF_TABLE,
    primaryKeys = [Constants.SERIES_ID_COLUMN, Constants.FUEL_ID_COLUMN]
)
data class SeriesAndFuelCrossRef(
    @ColumnInfo(name = Constants.SERIES_ID_COLUMN)
    val seriesId: Int,
    @ColumnInfo(name = Constants.FUEL_ID_COLUMN)
    val fuelTypId: Int

)

data class SeriesWithFeaturesAndFuelsRelation(
    @Embedded
    val series: SeriesEntity,
    @Relation(
        parentColumn = Constants.SERIES_ID_COLUMN,
        entityColumn = Constants.FEATURE_ID_COLUMN,
        associateBy = Junction(SeriesAndFeatureCrossRef::class)
    )
    val features: List<FeatureEntity>,

    @Relation(
        parentColumn = Constants.SERIES_ID_COLUMN,
        entityColumn = Constants.FUEL_ID_COLUMN,
        associateBy = Junction(SeriesAndFuelCrossRef::class)
    )
    val fuels: List<FuelTypeEntity>
)

data class BrandWithSeriesAndFeatureRelation(
    @Embedded
    val brand: BrandEntity,
    @Relation(
        entity = SeriesEntity::class,
        parentColumn = Constants.BRAND_ID_COLUMN,
        entityColumn = Constants.BRAND_ID_COLUMN
    )
    val series: List<SeriesWithFeaturesAndFuelsRelation>
)


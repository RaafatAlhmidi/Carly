package com.raafat.data.database.relations

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation
import com.raafat.data.database.Constants
import com.raafat.data.database.entities.FeatureEntity
import com.raafat.data.database.entities.SeriesEntity


@Entity(
    primaryKeys = [Constants.SERIES_ID_COLUMN, Constants.FEATURE_ID_COLUMN]
)
data class SeriesAndFeatureCrossRef(
    @ColumnInfo(name = Constants.SERIES_ID_COLUMN)
    val seriesId: Int,
    @ColumnInfo(name = Constants.FEATURE_ID_COLUMN)
    val featureId: Int
)

data class SeriesWithFeatureRelation(
    @Embedded
    val series: SeriesEntity,
    @Relation(
        parentColumn = Constants.SERIES_ID_COLUMN,
        entityColumn = Constants.FEATURE_ID_COLUMN,
        associateBy = Junction(SeriesAndFeatureCrossRef::class)
    )
    val features: List<FeatureEntity>
)


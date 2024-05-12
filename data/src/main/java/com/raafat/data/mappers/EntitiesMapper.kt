package com.raafat.data.mappers

import com.raafat.data.database.entities.FeatureEntity
import com.raafat.data.database.entities.FuelTypeEntity
import com.raafat.data.database.relations.BrandWithSeriesAndFeatureRelation
import com.raafat.data.database.relations.SeriesWithFeaturesAndFuelsRelation
import com.raafat.data.model.Brand
import com.raafat.data.model.Feature
import com.raafat.data.model.FuelType
import com.raafat.data.model.Series


fun BrandWithSeriesAndFeatureRelation.mapToModel(): Brand = Brand(
    name = this.brand.name,
    series = this.series.map { it.mapToModel() }
)

fun SeriesWithFeaturesAndFuelsRelation.mapToModel(): Series = Series(
    id = this.series.id,
    name = this.series.name,
    maximumYear = this.series.maximumYear,
    minimumYear = this.series.minimumYear,
    features = this.features.map { it.mapToModel() },
    fuelTypes = this.fuels.map { it.mapToModel() }
)

fun FeatureEntity.mapToModel(): Feature = Feature(
    id = this.id,
    name = this.name
)

fun FuelTypeEntity.mapToModel(): FuelType = FuelType(
    id = this.id,
    name = this.name
)
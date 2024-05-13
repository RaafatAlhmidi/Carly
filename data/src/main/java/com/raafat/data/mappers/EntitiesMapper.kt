package com.raafat.data.mappers

import com.raafat.data.database.entities.BrandEntity
import com.raafat.data.database.entities.CarEntity
import com.raafat.data.database.entities.FeatureEntity
import com.raafat.data.database.entities.FuelTypeEntity
import com.raafat.data.database.entities.SeriesEntity
import com.raafat.data.database.relations.BrandWithSeriesAndFeatureRelation
import com.raafat.data.database.relations.CarRelations
import com.raafat.data.database.relations.SeriesWithFeaturesAndFuelsRelation
import com.raafat.data.model.Brand
import com.raafat.data.model.Car
import com.raafat.data.model.Feature
import com.raafat.data.model.FuelType
import com.raafat.data.model.Series


fun BrandWithSeriesAndFeatureRelation.mapToModel(): Brand = Brand(
    id = this.brand.id,
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

fun SeriesEntity.mapToModel(): Series = Series(
    id = this.id,
    name = this.name,
    maximumYear = this.maximumYear,
    minimumYear = this.minimumYear,
    features = emptyList(),
    fuelTypes = emptyList()
)

fun FeatureEntity.mapToModel(): Feature = Feature(
    id = this.id,
    name = this.name
)

fun FuelTypeEntity.mapToModel(): FuelType = FuelType(
    id = this.id,
    name = this.name
)

fun BrandEntity.mapToModel(): Brand = Brand(
    id = this.id,
    name = this.name,
    series = emptyList()
)

fun CarRelations.mapToModel(): Car = Car(
    id = this.car.id ?: -1,
    brand = this.brand.mapToModel(),
    series = this.series.mapToModel(),
    year = this.car.year,
    fuelType = this.fuel.mapToModel(),
    isSelected = this.car.isSelected
)

fun Car.mapToEntity(): CarEntity = CarEntity(
    id = this.id,
    brandId = this.brand.id,
    seriesId = this.series.id,
    year = this.year,
    fuelType = this.fuelType.id,
    isSelected = this.isSelected
)
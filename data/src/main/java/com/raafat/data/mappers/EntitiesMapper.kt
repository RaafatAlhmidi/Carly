package com.raafat.data.mappers

import com.raafat.data.database.entities.BrandEntity
import com.raafat.data.database.entities.SeriesEntity
import com.raafat.data.model.Brand
import com.raafat.data.model.Series


fun BrandEntity.mapToModel(series: List<SeriesEntity>): Brand = Brand(
    name = this.name,
    series = series.map { it.mapToModel() }
)

fun SeriesEntity.mapToModel(): Series = Series(
    name = this.name,
    maximumYear = this.maximumYear,
    minimumYear = this.minimumYear
)
package com.raafat.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raafat.data.database.dao.BrandsDao
import com.raafat.data.database.dao.SeriesDao
import com.raafat.data.database.entities.BrandEntity
import com.raafat.data.database.entities.CarEntity
import com.raafat.data.database.entities.FeatureEntity
import com.raafat.data.database.entities.FuelTypeEntity
import com.raafat.data.database.entities.SeriesEntity
import com.raafat.data.database.relations.SeriesAndFeatureCrossRef
import com.raafat.data.database.relations.SeriesAndFuelCrossRef

@Database(
    entities = [
        BrandEntity::class,
        SeriesEntity::class,
        FeatureEntity::class,
        FuelTypeEntity::class,
        CarEntity::class,
        SeriesAndFuelCrossRef::class,
        SeriesAndFeatureCrossRef::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun brandsDao(): BrandsDao
    abstract fun seriesDao(): SeriesDao
}
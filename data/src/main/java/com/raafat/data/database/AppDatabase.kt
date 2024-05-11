package com.raafat.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raafat.data.database.dao.BrandsDao
import com.raafat.data.database.entities.BrandEntity
import com.raafat.data.database.entities.FeatureEntity
import com.raafat.data.database.entities.SeriesEntity
import com.raafat.data.database.relations.SeriesAndFeatureCrossRef

@Database(
    entities = [
        BrandEntity::class,
        SeriesEntity::class,
        FeatureEntity::class,
        SeriesAndFeatureCrossRef::class
    ], version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun brandsDao(): BrandsDao
}
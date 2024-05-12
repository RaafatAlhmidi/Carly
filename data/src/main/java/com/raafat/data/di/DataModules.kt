package com.raafat.data.di

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.raafat.data.R
import com.raafat.data.database.AppDatabase
import com.raafat.data.database.Constants
import com.raafat.data.database.dao.BrandsDao
import com.raafat.data.database.dao.SeriesDao
import com.raafat.data.repository.brands.CarsRepository
import com.raafat.data.repository.brands.CarsRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindCarsRepository(repository: CarsRepositoryImp): CarsRepository
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Throws(Exception::class)
    private fun populateDatabase(db: SupportSQLiteDatabase, lines: List<String>) {
        var currentBrand: String? = null
        var currentFeatures: String? = null

        for (i in lines.indices) {
            val line = lines[i]
            val values = line.split(",")
            val brand = values[0].run {
                if (this.isBlank().not()) {
                    currentBrand = this
                }
                currentBrand
            }
            val series = values[1]
            val from = values[2]
            val to = values[3]
            val features = values[4].run {
                if (this.isBlank().not()) {
                    currentFeatures = this
                }
                currentFeatures
            }?.split("|")

            val brandsContentValue = ContentValues().apply {
                put(Constants.BRAND_NAME_COLUMN, brand)
            }
            db.insert(Constants.BRAND_TABLE, SQLiteDatabase.CONFLICT_REPLACE, brandsContentValue)

            features?.onEach { feature ->
                val featureContentValues = ContentValues().apply {
                    put(Constants.FEATURE_NAME_COLUMN, feature)
                }
                db.insert(Constants.FEATURE_TABLE, SQLiteDatabase.CONFLICT_IGNORE, featureContentValues)

                val seriesAndFeatureContentValue = ContentValues().apply {
                    put(Constants.SERIES_ID_COLUMN, series)
                    put(Constants.FEATURE_NAME_COLUMN, feature)
                }
                db.insert(Constants.SERIES_AND_FEATURE_CROSS_REF_TABLE, SQLiteDatabase.CONFLICT_IGNORE, seriesAndFeatureContentValue)
            }

            val seriesContentValues = ContentValues().apply {
                put(Constants.SERIES_NAME_COLUMN, series)
                put(Constants.SERIES_MINIMUM_YEAR_COLUMN, from)
                put(Constants.SERIES_MAXIMUM_YEAR_COLUMN, to)
                put(Constants.BRAND_NAME_COLUMN, brand)
            }
            db.insert(Constants.SERIES_TABLE, SQLiteDatabase.CONFLICT_REPLACE, seriesContentValues)


        }
    }

    private fun readLinesFromRawResource(context: Context, resourceId: Int): List<String> {
        val inputStream = context.resources.openRawResource(resourceId)
        val reader = inputStream.bufferedReader()
        return reader.readLines()
    }

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "cars.db"
        ).addCallback(callback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                val lines = readLinesFromRawResource(context, R.raw.data)
                db.beginTransaction()
                //the first line is the header
                kotlin.runCatching {
                    populateDatabase(db, lines.subList(1, lines.lastIndex))
                }.onSuccess {
                    db.setTransactionSuccessful()
                }.onFailure { it.printStackTrace() }
                db.endTransaction()
            }
        }).build()
    }

    @Provides
    fun provideBrandsDao(database: AppDatabase): BrandsDao = database.brandsDao()
    @Provides
    fun provideCarsDao(database: AppDatabase): SeriesDao = database.seriesDao()

}
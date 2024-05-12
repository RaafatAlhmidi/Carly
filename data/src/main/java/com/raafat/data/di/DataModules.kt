package com.raafat.data.di

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
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

    /**
     * this function check if this feature exist in the database and return the id of it,
     * otherwise it return -1
     */
    private fun getFeatureId(db: SupportSQLiteDatabase, feature: String): Int {
        return db.query("SELECT ${Constants.FEATURE_ID_COLUMN} FROM ${Constants.FEATURE_TABLE} where ${Constants.FEATURE_NAME_COLUMN} = '$feature'")
            .run {
                if (moveToFirst()) {
                    getInt(0)
                } else {
                    -1
                }
            }
    }


    /**
     * this function insert a feature in the database and return the id of it
     */
    private fun insertFeature(db: SupportSQLiteDatabase, feature: String): Int {
        val featureContentValues = ContentValues().apply {
            put(Constants.FEATURE_NAME_COLUMN, feature)
        }
        return db.insert(Constants.FEATURE_TABLE, SQLiteDatabase.CONFLICT_IGNORE, featureContentValues).toInt()
    }

    /**
     * this function insert a series in the database and return the id of it
     */
    private fun insertSeries(db: SupportSQLiteDatabase, series: String, from: Int, to: Int, brandId:Int): Int {
        val seriesContentValues = ContentValues().apply {
            put(Constants.SERIES_NAME_COLUMN, series)
            put(Constants.SERIES_MINIMUM_YEAR_COLUMN, from)
            put(Constants.SERIES_MAXIMUM_YEAR_COLUMN, to)
            put(Constants.BRAND_ID_COLUMN, brandId)
        }
        return db.insert(Constants.SERIES_TABLE, SQLiteDatabase.CONFLICT_REPLACE, seriesContentValues).toInt()
    }

    /**
     * this function inserts a brand in the database and return the id of it
     */
    private fun insertBrand(db: SupportSQLiteDatabase, brand: String): Int {
        val brandsContentValue = ContentValues().apply {
            put(Constants.BRAND_NAME_COLUMN, brand)
        }
        return db.insert(Constants.BRAND_TABLE, SQLiteDatabase.CONFLICT_REPLACE, brandsContentValue).toInt()
    }

    /**
     * this function check if this brand exist in the database and return the id of it,
     * otherwise it return -1
     */
    private fun getBrandId(db: SupportSQLiteDatabase, brand: String): Int{
        return db.query("SELECT ${Constants.BRAND_ID_COLUMN} FROM ${Constants.BRAND_TABLE} where ${Constants.BRAND_NAME_COLUMN} = '$brand'").run {
            if (moveToFirst()) {
                getInt(0)
            } else {
                -1
            }
        }
    }

    /**
     * this function insert a series and feature in the database,
     * this table represent the many to many relationship between series and features
     */
    private fun insertSeriesAndFeature(db: SupportSQLiteDatabase, seriesId: Int, featureId: Int){
        val seriesAndFeatureContentValue = ContentValues().apply {
            put(Constants.SERIES_ID_COLUMN, seriesId)
            put(Constants.FEATURE_ID_COLUMN, featureId)
        }
        db.insert(Constants.SERIES_AND_FEATURE_CROSS_REF_TABLE, SQLiteDatabase.CONFLICT_IGNORE, seriesAndFeatureContentValue)
    }

    /**
     * this function check if this fuel type exist in the database and return the id of it,
     * otherwise it return -1
     */
    private fun getFuelId(db: SupportSQLiteDatabase, fuel: String): Int{
        return db.query("SELECT ${Constants.FUEL_ID_COLUMN} FROM ${Constants.FUEL_TYPE_TABLE} where ${Constants.FUEL_TYPE_NAME_COLUMN} = '$fuel'")
            .run {
                if (moveToFirst()) {
                    getInt(0)
                } else {
                    -1
                }
            }
    }

    /**
     * this function insert a fuel type in the database and return the id of it
     */
    private fun insertFuelType(db: SupportSQLiteDatabase, fuel: String): Int {
        val fuelContentValues = ContentValues().apply {
            put(Constants.FUEL_TYPE_NAME_COLUMN, fuel)
        }
        return db.insert(Constants.FUEL_TYPE_TABLE, SQLiteDatabase.CONFLICT_IGNORE, fuelContentValues).toInt()
    }


    /**
     * this function insert a series and fuel type in the database,
     * this table represent the many to many relationship between series and fuelType
     */
    private fun insertSeriesAndFuel(db: SupportSQLiteDatabase, seriesId: Int, fuelId: Int){
        val seriesAndFuelContentValue = ContentValues().apply {
            put(Constants.SERIES_ID_COLUMN, seriesId)
            put(Constants.FUEL_ID_COLUMN, fuelId)
        }
        db.insert(Constants.SERIES_AND_FUEL_CROSS_REF_TABLE, SQLiteDatabase.CONFLICT_IGNORE, seriesAndFuelContentValue)
    }


    @Throws(Exception::class)
    private fun populateDatabase(db: SupportSQLiteDatabase, lines: List<String>) {
        var currentBrand: String? = null
        var currentFeatures: String? = null
        val fuels = listOf("Gasoline", "Diesel", "Hybrid", "Electric", "Other")

        for (i in lines.indices) {
            val line = lines[i]
            val values = line.split(",")
            val brand = values[0].run {
                if (this.isBlank().not()) {
                    currentBrand = this
                }
                currentBrand ?: this
            }
            val series = values[1]
            val from = values[2]
            val to = values[3]
            val features = values[4].run {
                if (this.isBlank().not()) {
                    currentFeatures = this
                }
                currentFeatures ?: this
            }.split("|")

            var brandId = getBrandId(db, brand)
            if (brandId == -1)
                insertBrand(db, brand)

            val seriesId = insertSeries(db = db, series = series, from = from.toInt(), to = to.toInt(), brandId = brandId)

            features.onEach { feature ->

                var featureId = getFeatureId(db, feature)
                if (featureId == -1)
                    featureId = insertFeature(db, feature)

                insertSeriesAndFeature(db = db, seriesId = seriesId, featureId = featureId)

            }

            fuels.onEach { fuelType ->

                val fuelId = getFuelId(db, fuelType)
                if (fuelId == -1)
                    insertFuelType(db, fuelType)
                insertSeriesAndFuel(db = db, seriesId = seriesId, fuelId = fuelId)
            }


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
                    Log.i("Raafat", "success")
                    db.setTransactionSuccessful()
                }.onFailure {
                    it.printStackTrace()
                }
                db.endTransaction()
            }
        }).build()
    }

    @Provides
    fun provideBrandsDao(database: AppDatabase): BrandsDao = database.brandsDao()

    @Provides
    fun provideCarsDao(database: AppDatabase): SeriesDao = database.seriesDao()

}
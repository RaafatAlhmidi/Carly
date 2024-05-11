package com.raafat.data.di

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.raafat.data.database.AppDatabase
import com.raafat.data.database.Constants
import com.raafat.data.database.dao.BrandsDao
import com.raafat.data.repository.CarsRepository
import com.raafat.data.repository.CarsRepositoryImp
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

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "cars.db"
        )
            .addCallback(callback = object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    db.beginTransaction()
                    val contentValues = ContentValues()
                    contentValues.put(Constants.BRAND_NAME_COLUMN, "BMW")
                    db.insert(Constants.BRAND_TABLE, SQLiteDatabase.CONFLICT_ABORT, contentValues)
                    db.insert(Constants.BRAND_TABLE, SQLiteDatabase.CONFLICT_ABORT, contentValues)

                    db.setTransactionSuccessful()
                    db.endTransaction()
                }
            })
            .build()
    }

    @Provides
    fun provideBrandsDao(database: AppDatabase): BrandsDao = database.brandsDao()
}
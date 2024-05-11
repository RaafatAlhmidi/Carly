package com.raafat.data.di

import android.content.Context
import androidx.room.Room
import com.raafat.data.database.AppDatabase
import com.raafat.data.database.dao.BrandsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

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
        ).build()
    }

    @Provides
    fun provideBrandsDao(database: AppDatabase): BrandsDao = database.brandsDao()
}
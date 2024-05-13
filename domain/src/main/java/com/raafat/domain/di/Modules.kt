package com.raafat.domain.di

import com.raafat.domain.services.carCreation.CarCreationService
import com.raafat.domain.services.carCreation.CarCreationServiceImpl
import com.raafat.domain.services.carSelection.CarSelectionService
import com.raafat.domain.services.carSelection.CarSelectionServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ServicesModule {

    @Singleton
    @Binds
    abstract fun bindCarsCreationService(service: CarCreationServiceImpl): CarCreationService

    @Singleton
    @Binds
    abstract fun bindCarSelectionService(service: CarSelectionServiceImpl): CarSelectionService

}
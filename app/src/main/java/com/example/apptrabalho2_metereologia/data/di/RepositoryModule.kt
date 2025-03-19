package com.example.apptrabalho2_metereologia.data.di

import com.example.apptrabalho2_metereologia.data.repository.WeatherRepository
import com.example.apptrabalho2_metereologia.data.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindWeatherRepository(repository: WeatherRepositoryImpl) : WeatherRepository

}
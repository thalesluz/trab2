package com.example.apptrabalho2_metereologia.data.di

import com.example.apptrabalho2_metereologia.data.KtorRemoteDataSource
import com.example.apptrabalho2_metereologia.data.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    @Singleton
    fun bindRemoteDataSource(remoteDataSource: KtorRemoteDataSource): RemoteDataSource // Instância do KtorRemoteDataSource
}
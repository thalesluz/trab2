package com.example.apptrabalho2_metereologia.data.remote.di

import android.content.Context
import com.example.apptrabalho2_metereologia.data.KtorRemoteDataSource
import com.example.apptrabalho2_metereologia.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = io.ktor.client.plugins.logging.LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        httpClient: HttpClient,
        @ApplicationContext context: Context
    ): RemoteDataSource {
        return KtorRemoteDataSource(httpClient, context)
    }
}
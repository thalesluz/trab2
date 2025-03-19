package com.example.apptrabalho2_metereologia.data

import WeatherDataResponse
import com.example.apptrabalho2_metereologia.data.remote.RemoteDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class KtorRemoteDataSource @Inject constructor (
    private val httpClient: HttpClient,
) : RemoteDataSource {

    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5"
    }

    override suspend fun getWeatherDataResponse(lat: Float, lng: Float): WeatherDataResponse {
        return httpClient
            .get("${BASE_URL}/weather?lat=$lat&lon=$lng&appid=5b5f98961e56c912598e623a6b92d650&units=metric")
            .body()
    }
}
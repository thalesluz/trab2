package com.example.apptrabalho2_metereologia.data.remote

import com.example.apptrabalho2_metereologia.data.remote.response.WeatherDataResponse

interface RemoteDataSource {

    suspend fun getWeatherDataResponse(lat: Float, lng: Float): WeatherDataResponse
}
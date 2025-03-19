package com.example.apptrabalho2_metereologia.data.remote

import WeatherDataResponse

interface RemoteDataSource {

    suspend fun getWeatherDataResponse(lat:Float, lng:Float): WeatherDataResponse
}
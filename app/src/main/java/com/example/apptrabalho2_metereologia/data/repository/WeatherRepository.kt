package com.example.apptrabalho2_metereologia.data.repository

import com.example.apptrabalho2_metereologia.data.model.WeatherInfo

interface WeatherRepository {
    suspend fun  getWeatherData(lat: Float, lng: Float): WeatherInfo
}
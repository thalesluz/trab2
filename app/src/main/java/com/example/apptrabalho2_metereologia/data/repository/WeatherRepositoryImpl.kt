package com.example.apptrabalho2_metereologia.data.repository

import com.example.apptrabalho2_metereologia.data.model.WeatherInfo
import com.example.apptrabalho2_metereologia.data.remote.RemoteDataSource
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : WeatherRepository{
    override suspend fun getWeatherData(lat: Float, lng: Float): WeatherInfo {
        val response = remoteDataSource.getWeatherDataResponse(lat, lng)
        val weather = response.weather[0]

        return WeatherInfo(
            locationName = response.name,
            conditionIcon = weather.icon,
            condition = weather.main,
            temperature = response.main.temp.toInt(),
            dayOfWeek = LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()),
            isDay = weather.icon.last() == 'd'
        )
    }
}
package com.example.apptrabalho2_metereologia.ui.feature

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptrabalho2_metereologia.data.repository.WeatherRepository
import com.example.apptrabalho2_metereologia.utils.LocationHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewodel @Inject constructor(
    private val weatherRepository : WeatherRepository,
    private val application: Application
) : ViewModel() {

    private val _weatherInfoState = MutableStateFlow(WeatherInfoState())
    val weatherInfoState: StateFlow<WeatherInfoState> = _weatherInfoState.asStateFlow()
    private val _customLocation = MutableStateFlow<Pair<Float?, Float?>?>(null)
    fun updateLocation(coordinates: Pair<Float?, Float?>) {
        _customLocation.value = coordinates
        getWeatherInfo()
    }
    init {
        getWeatherInfo()
    }

    private fun getWeatherInfo() {
        viewModelScope.launch {
            val locationHelper = LocationHelper(application.applicationContext)
            try {
                val location = locationHelper.getCurrentLocation()
                if (location != null) {
                    val latitude = _customLocation.value?.first ?: location.latitude.toFloat()
                    val longitude = _customLocation.value?.second ?: location.longitude.toFloat()
                    val weatherInfo = weatherRepository.getWeatherData(latitude, longitude)
                    _weatherInfoState.update {
                        it.copy(weatherInfo = weatherInfo)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
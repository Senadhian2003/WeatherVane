package com.example.weathervane.data.repository

import com.example.weathervane.data.remote.WeatherApiService
import com.example.weathervane.model.WeatherModel

class RepositoryImpl(private val apiService: WeatherApiService) : Repository {
    override suspend fun fetchWeatherData(city : String): WeatherModel {
        println("Fetching weather data from api")
        return apiService.getWeatherData( apiKey = "3ff3dd44386b4bf4ae4180412242611",
            location = city)
    }

//    override suspend fun fetchWeatherDataforCurrentLocation(
//        latitude: Double,
//        longitude: Double
//    ): WeatherModel {
//        return apiService.getWeatherDataForCurrentLocation( apiKey = "3ff3dd44386b4bf4ae4180412242611",
//            latitude = latitude, longitude = longitude)
//    }
}
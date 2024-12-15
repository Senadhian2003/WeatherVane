package com.example.weathervane.data.repository

import com.example.weathervane.model.WeatherModel

interface Repository {

    suspend fun fetchWeatherData(city : String ) : WeatherModel

//    suspend fun fetchWeatherDataforCurrentLocation(latitude : Double, longitude : Double ) : WeatherModel

}
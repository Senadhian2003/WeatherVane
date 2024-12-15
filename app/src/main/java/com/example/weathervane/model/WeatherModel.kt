package com.example.weathervane.model

data class WeatherModel(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)
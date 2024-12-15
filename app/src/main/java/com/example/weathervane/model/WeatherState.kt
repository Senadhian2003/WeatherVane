package com.example.weathervane.model

data class WeatherState(
    val isLoading : Boolean = true,
    val weather : WeatherModel?=null,
    val city : String="Chennai",
    val error: String? = null

)
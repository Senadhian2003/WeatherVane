package com.example.weathervane.data.remote

import com.example.weathervane.model.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("forecast.json")
    suspend fun getWeatherData(
        @Query("key") apiKey: String,
        @Query("q") location: String
    ): WeatherModel

}
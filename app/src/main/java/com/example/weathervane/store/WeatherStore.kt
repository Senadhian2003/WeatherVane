package com.example.weathervane.store

import com.example.weathervane.model.WeatherModel
import com.example.weathervane.model.WeatherState
import org.reduxkotlin.Reducer

val weatherReducer: Reducer<WeatherState> = { state, action ->
    when (action) {
        is WeatherAction.FetchWeatherData -> {
            state.copy(isLoading = true, error = null)
        }
        is WeatherAction.FetchWeatherDataSuccess -> {
            state.copy(
                isLoading = false,
                weather = action.weather,
                error = null
            )
        }
        is WeatherAction.FetchWeatherDataError -> {
            state.copy(
                isLoading = false,
                error = action.error
            )
        }
        is WeatherAction.UpdateCity -> {
            state.copy(
                city = action.city
            )
        }
        else -> state
    }
}

// Actions
sealed class WeatherAction {
    object FetchWeatherData : WeatherAction()
    data class FetchWeatherDataSuccess(val weather: WeatherModel) : WeatherAction()
    data class FetchWeatherDataError(val error: String) : WeatherAction()
    data class UpdateCity(val city: String) : WeatherAction()
}


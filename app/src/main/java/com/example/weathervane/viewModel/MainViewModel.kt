package com.example.weathervane.viewModel

import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.weathervane.data.repository.Repository
import com.example.weathervane.model.WeatherModel
import com.example.weathervane.store.WeatherAction
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.reduxkotlin.Store
import javax.inject.Inject
import com.example.weathervane.model.WeatherState
@HiltViewModel
class MainViewModel @Inject constructor(
    private  val repository: Repository,
    private val store: Store<WeatherState>,
    @ApplicationContext private val context: Context
) : ViewModel() {

//    Location
private val locationClient = LocationServices.getFusedLocationProviderClient(context)

    private val _locationState =   mutableStateOf("Fetching Details")
    val locationState : State<String> = _locationState



    fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            try {
                locationClient.lastLocation
                    .addOnSuccessListener { location ->
                        if (location != null) {
                            _locationState.value = "Lat: ${location.latitude}, Long: ${location.longitude}"


                            fetchData("${location.latitude},${location.longitude}")

                            print("LOCATION : _______${_locationState.value}" )
                        } else {
                            _locationState.value = "Please enable GPS and try again"
                            print("LOCATION : _______${_locationState.value}" )
                        }
                    }
                    .addOnFailureListener { exception ->
                        _locationState.value = when (exception) {
                            is SecurityException -> "Location permission denied"
                            is ResolvableApiException -> "Location settings are not satisfied"
                            else -> "Error getting location: ${exception.message}"
                        }
                    }
            } catch (e: Exception) {
                _locationState.value = "Error: ${e.message}"
                store.dispatch(WeatherAction.FetchWeatherDataError("Error : ${e.message}"))


            }
        } else {
            _locationState.value = "Location permissions not granted"
            store.dispatch(WeatherAction.FetchWeatherDataError("Location permissions not granted"))

        }
    }



    private val _weatherState = mutableStateOf(WeatherState())
    val weatherState : State<WeatherState> = _weatherState

    init {
        store.subscribe {
            _weatherState.value = store.state
        }
        getLocation()
    }

     fun fetchData(searchLocation : String){

        viewModelScope.launch {

            store.dispatch(WeatherAction.FetchWeatherData)
            try {

                val response = repository.fetchWeatherData(searchLocation)


                store.dispatch(WeatherAction.FetchWeatherDataSuccess(response))
                updateCity(response.location.name)

            }
            catch (e : Exception){
                print("ERROR : ${e.message}")

                store.dispatch(WeatherAction.FetchWeatherDataError("Error retrieving weather data : ${e.message}"))

            }



        }


    }

    fun updateCity(newCity: String) {
        store.dispatch(WeatherAction.UpdateCity(newCity))
    }



}


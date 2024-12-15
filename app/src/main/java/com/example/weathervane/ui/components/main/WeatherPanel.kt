package com.example.weathervane.ui.components.main

import HourlyForecastPanel
import WeatherMetaDataPanel
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
//import com.example.weathervane.Manifest
import com.example.weathervane.model.WeatherModel
import com.example.weathervane.viewModel.MainViewModel
//import com.example.weathervane.viewModel.MainViewModel
import java.text.SimpleDateFormat
import java.util.Locale

//val customPurpleColor = Color(0xFF37176E)
////val customPurpleColor = Color(0xAA37176E)

fun formatApiDate(apiDate: String): String {
    // Define the input format based on the API data
    val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
    // Define the output format as desired
    val outputFormat = SimpleDateFormat("EEE MMM d | hh:mm a", Locale.getDefault())

    // Parse the API date string into a Date object
    val date = inputFormat.parse(apiDate)
    // Format the Date object into the desired string format
    return outputFormat.format(date ?: "")
}



@Preview
@Composable
fun WeatherPanel(weather : WeatherModel, weatherViewModel: MainViewModel) {

//    var isLocationInputVisible : MutableState<Boolean> by remember { mutableStateOf(true) }
    var isLocationInputVisible by remember { mutableStateOf(false) }


    val context = LocalContext.current

//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestMultiplePermissions()
//    ) { permissions ->
//        if (permissions.values.all { it }) {
//            // All permissions granted, proceed with location fetch
////            weatherViewModel.getLocation()
//        }
//    }

//    LaunchedEffect(Unit) {
//        if (ActivityCompat.checkSelfPermission(
//                context,
//                android.Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED &&
//            ActivityCompat.checkSelfPermission(
//                context,
//                android.Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // Request permissions using the launcher
//            launcher.launch(
//                arrayOf(
//                    android.Manifest.permission.ACCESS_FINE_LOCATION,
//                    android.Manifest.permission.ACCESS_COARSE_LOCATION
//                )
//            )
//        } else {
//            // Permissions already granted, proceed with location fetch
////            weatherViewModel.getLocation()
//        }
//    }


    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(android.graphics.Color.parseColor("#59469d")),
                        Color(android.graphics.Color.parseColor("#643d67"))
                    )
                )
            )
    ) {




        // Sticky Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp) // Adjust height as needed
                .zIndex(1f) // Keeps this box above the content
                .graphicsLayer(clip = true)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(android.graphics.Color.parseColor("#59469d")),
                            Color(android.graphics.Color.parseColor("#643d67"))
                        )
                    )
                )
        ) {

        }

        // Scrollable Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(16.dp,40.dp), // Push content below sticky header
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            Text("${weather.current.condition.text}", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
//            Text(text= "HELLLO BOIIISS ${weatherViewModel.locationState.value}")
            AsyncImage(
                model = "https://${weather.current.condition.icon.replace("64x64","128x128")}",
                contentDescription = null,
                modifier = Modifier
                    .size(280.dp) // Smaller size
                    .padding(4.dp) // Add some padding
                    .aspectRatio(1f)
            )
            Spacer(modifier = Modifier.height(0.dp))
            Text(formatApiDate(weather.location.localtime), color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Normal)
            Spacer(modifier = Modifier.height(16.dp))
            Text("${weather.current.temp_c}°c", color = Color.White, fontSize = 72.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))




            if (isLocationInputVisible) {
                OutlinedTextField(
                    value = weatherViewModel.weatherState.value.city,
                    onValueChange = { weatherViewModel.updateCity(it) },
                    label = { Text("Enter City") },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                weatherViewModel.fetchData(weatherViewModel.weatherState.value.city)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search"
                            )
                        }
                    },
//                    modifier = Modifier.fillMaxWidth() // Make sure width is consistent
                )
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth() // Match the width of OutlinedTextField
                        .padding(8.dp, 0.dp),
                    horizontalArrangement = Arrangement.Center, // Center the entire row
                    verticalAlignment = Alignment.CenterVertically // Align items vertically
                ) {
                    IconButton(
                        onClick = {
                            isLocationInputVisible = !isLocationInputVisible
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Location Icon",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Text(
                        weather.location.name,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }


            // Weather Meta Data Panel
            WeatherMetaDataPanel(weather)

            // Future Forecast Panel
            HourlyForecastPanel(weather)
        }
    }
}



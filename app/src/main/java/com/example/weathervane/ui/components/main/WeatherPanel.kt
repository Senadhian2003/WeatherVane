package com.example.weathervane.ui.components.main

import HourlyForecastPanel
import WeatherMetaDataPanel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage

//val customPurpleColor = Color(0xFF37176E)
////val customPurpleColor = Color(0xAA37176E)

@Preview
@Composable
fun WeatherPanel() {
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
                .padding(top = 60.dp), // Push content below sticky header
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            Text("Mostly Cloudy", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Normal)
            Spacer(modifier = Modifier.height(16.dp))

            AsyncImage(
                model = "https://cdn-icons-png.flaticon.com/512/6122/6122714.png",
                contentDescription = null,
            )
            Spacer(modifier = Modifier.height(0.dp))
            Text("Mon June 17 | 10:00 AM", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Normal)
            Spacer(modifier = Modifier.height(16.dp))
            Text("25°", color = Color.White, fontSize = 72.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("H:27 L:18", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Light)

            // Weather Meta Data Panel
            WeatherMetaDataPanel()

            // Future Forecast Panel
            HourlyForecastPanel()
        }
    }
}

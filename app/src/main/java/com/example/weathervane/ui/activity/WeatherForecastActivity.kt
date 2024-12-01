package com.example.weathervane.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weathervane.R
import com.example.weathervane.ui.components.main.WeatherPanel

import com.example.weathervane.ui.theme.WeatherVaneTheme
import com.example.weathervane.ui.theme.customPurpleColor

class WeatherForecastActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_weather_forecast)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        setContent {
            WeatherVaneTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WeatherForecastScreen()
                }
            }
        }

//        setContent {
//            WeatherForecastScreen()
//        }

    }
}

@Composable
fun WeatherForecastScreen(){
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
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,  // Padding from the left
                    end = 16.dp,    // Padding from the right
                    top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding() + 16.dp // Padding for the status bar

                ) // Add padding for status bar
        ) {

            Image(painter = painterResource(R.drawable.back), contentDescription = "Navigate to Main Activity", modifier = Modifier.clickable {

                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            })

            Column(modifier = Modifier.height(200.dp).fillMaxWidth().background(color = customPurpleColor, shape = RoundedCornerShape(16.dp))) {



            }



        }


    }

    }
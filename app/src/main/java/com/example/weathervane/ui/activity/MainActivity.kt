package com.example.weathervane.ui.activity

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.collection.emptyLongSet
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column



import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weathervane.ui.components.main.WeatherPanel
import com.example.weathervane.ui.theme.WeatherVaneTheme
import com.example.weathervane.viewModel.MainViewModel
//import com.example.weathervane.viewModel.MainViewModel
//import com.example.weathervane.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            WeatherVaneTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->


                    var permissionsGranted by remember { mutableStateOf(false) }
                    val context = LocalContext.current

                    val launcher = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.RequestMultiplePermissions()
                    ) { permissions ->
                        permissionsGranted = permissions.values.all { it }
                    }

                    LaunchedEffect(Unit) {
                        // Check if permissions are already granted
                        permissionsGranted = checkPermissions(context)
                        if (!permissionsGranted) {
                            launcher.launch(
                                arrayOf(
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                                )
                            )
                        }
                    }




                    if(permissionsGranted){
                    val weatherViewModel  = hiltViewModel<MainViewModel>()
                    val viewState by weatherViewModel.weatherState
                    when {

                        viewState.isLoading -> {
                            Column(
                                modifier = Modifier.padding(25.dp).fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CircularProgressIndicator()
                            }


                        }

                        viewState.error != null -> {

                            Column(modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                Text("An Error occurred ${viewState.error}")

                            }

                               }

                        else -> {
//                            CategoryScreen(viewState.list)
                            viewState.weather?.let { WeatherPanel(it, weatherViewModel) }
//                            Text("SUCCESS")
                        }

                    }




                    }
                    else{
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Location permissions are required to use this app")
                        }
                    }


                }
            }
        }
    }
}

private fun checkPermissions(context: Context): Boolean {
    return ActivityCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
}


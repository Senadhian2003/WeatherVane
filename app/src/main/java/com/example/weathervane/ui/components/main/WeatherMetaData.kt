import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weathervane.R
import com.example.weathervane.model.WeatherModel
import com.example.weathervane.ui.theme.customPurpleColor


@Composable
fun WeatherMetaDataPanel(weather : WeatherModel){

    Row(
        modifier = Modifier.fillMaxWidth().padding(0.dp,24.dp).background(color = customPurpleColor, shape = RoundedCornerShape(16.dp)),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        WeatherMetaData(R.drawable.rain, "${weather.forecast.forecastday[0].day.daily_chance_of_rain}%", "Rain")
        WeatherMetaData(R.drawable.wind, "${weather.current.wind_kph}Km/hr", "Wind")
        WeatherMetaData(R.drawable.humidity, "${weather.current.humidity}%", "Humidity")
    }

}


@Composable
fun WeatherMetaData(imageResId : Int, value : String, label : String){


    Column(modifier = Modifier.padding(0.dp,32.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(
            painter = painterResource(id = imageResId), // Replace with your icon
            contentDescription = "Rain Icon",
            modifier = Modifier.size(48.dp)
        )
        Text(
            text = value,
            color = Color.White, // Adjust color as needed
            fontSize = 14.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Text(
            text = label,
            color = Color.White, // Adjust color as needed
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal
        )


    }


}

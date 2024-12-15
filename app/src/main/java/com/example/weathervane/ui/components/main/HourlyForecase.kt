import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.weathervane.R
import com.example.weathervane.model.Hour
import com.example.weathervane.model.WeatherModel
import com.example.weathervane.ui.activity.WeatherForecastActivity
import com.example.weathervane.ui.theme.customPurpleColor

@Composable
fun HourlyForecastPanel(weather : WeatherModel){

    Column {

        Row(modifier = Modifier.fillMaxWidth().padding(0.dp, 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Today",
                color = Color.Yellow, // Adjust color as needed
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold
            )
            val context = LocalContext.current
            Text(
                text = "Next 7 Days >",
                color = Color.White, // Adjust color as needed
                fontSize = 16.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.clickable {

                    val intent = Intent(context, WeatherForecastActivity::class.java)
                    context.startActivity(intent)
                }
            )

        }

        Row(modifier = Modifier.padding(0.dp,16.dp).horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(16.dp),

            ) {

            val hourlyData: List<Hour> = weather.forecast?.forecastday?.getOrNull(0)?.hour ?: emptyList()

            hourlyData.forEach{
                    hourly ->
                HourlyForecastData(hour = hourly)
            }
        }






    }

}


@Composable
fun HourlyForecastData(hour: Hour){

    Column(modifier = Modifier.width(96.dp).background(color = customPurpleColor, shape = RoundedCornerShape(16.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = "${hour.time.substringAfter(" ")}",
            color = Color.White, // Adjust color as needed
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(0.dp, 8.dp)

        )

        AsyncImage(
            model = "https://${hour.condition.icon}",
            contentDescription = null,
            modifier = Modifier.aspectRatio(1f).size(40.dp)
        )
//        Image(painter = painterResource(R.drawable.windy), contentDescription = "Forecast weather image" )

        Text(
            text = "${hour.temp_c}°",
            color = Color.White, // Adjust color as needed
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(0.dp, 8.dp)

        )


    }


}
package com.weather.nowlater

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.current.weather.view.HomeScreen
import com.weather.city.view.CitySearchScreen
import com.weather.core.Constants.CITY_LAT
import com.weather.core.Constants.CITY_LON
import com.weather.core.Constants.CITY_NAME
import com.weather.core.Constants.CITY_SEARCH_SCREEN
import com.weather.core.Constants.DAYS_FORECAST_SCREEN
import com.weather.core.Constants.HOME_SCREEN
import com.weather.dayforecast.view.DaysTempScreen
import com.weather.nowlater.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppCreation()
        }
    }
}

@Composable
fun AppCreation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = CITY_SEARCH_SCREEN) {
        composable(CITY_SEARCH_SCREEN) { CitySearchScreen(navController) }
        composable("$HOME_SCREEN/{$CITY_NAME}/{$CITY_LAT}/{$CITY_LON}") { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString(CITY_NAME)
            val cityLat = backStackEntry.arguments?.getString(CITY_LAT)
            val cityLon = backStackEntry.arguments?.getString(CITY_LON)
            HomeScreen(navController, cityName ?: "", cityLat ?: "", cityLon ?: "")
        }

        composable("$DAYS_FORECAST_SCREEN/{$CITY_LAT}/{$CITY_LON}") { backStackEntry ->
            val cityLat = backStackEntry.arguments?.getString(CITY_LAT)
            val cityLon = backStackEntry.arguments?.getString(CITY_LON)
            DaysTempScreen(  cityLat ?: "", cityLon ?: "")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        AppCreation()
    }
}
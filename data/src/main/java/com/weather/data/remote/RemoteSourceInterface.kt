package com.weather.data.remote

import com.weather.data.model.CitySearchResponse
import com.weather.data.model.WeatherResponse
import retrofit2.Response


interface RemoteSourceInterface {
    suspend fun getWeather(
        lat: Double,
        lon: Double,
    ): Response<WeatherResponse>

    suspend fun searchForCityByName(cityName:String):Response<List<CitySearchResponse>>
}
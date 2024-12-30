package com.weather.data.remote

import com.weather.data.model.CitySearchResponse
import com.weather.data.model.WeatherResponse
import retrofit2.Response
import javax.inject.Inject

class RemoteSourceImp @Inject constructor(private val services: Services) :
    RemoteSourceInterface {
    override suspend fun getWeather(
        lat: Double,
        lon: Double,

    ): Response<WeatherResponse> {
        return services.getWeather(lat, lon)
    }

    override suspend fun searchForCityByName(cityName: String): Response<List<CitySearchResponse>> {
        return services.searchForCityByName(cityName)
    }

}
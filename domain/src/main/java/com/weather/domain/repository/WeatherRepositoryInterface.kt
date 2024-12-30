package com.weather.domain.repository

import com.weather.core.DataHolder
import com.weather.domain.model.CitySearchResponseDomainModel
import com.weather.domain.model.WeatherDomainModel

interface WeatherRepositoryInterface {
    suspend fun getWeather(lat: Double, lon: Double): DataHolder<WeatherDomainModel>
    suspend fun searchForCityByName(cityName: String): DataHolder<List<CitySearchResponseDomainModel>>
}
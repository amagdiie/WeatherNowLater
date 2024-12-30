package com.weather.data.local

import com.weather.data.model.CityEntity

interface LocalSourceInterface {
    suspend fun getAllCities(): List<CityEntity>
    suspend fun insertCities(city: CityEntity)
}
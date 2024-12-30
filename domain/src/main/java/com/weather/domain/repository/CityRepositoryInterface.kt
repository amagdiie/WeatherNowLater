package com.weather.domain.repository

import com.weather.core.DataHolder
import com.weather.domain.model.CityDomainModel

interface CityRepositoryInterface {
    suspend fun getAllCities(): DataHolder<List<CityDomainModel>>
    suspend fun insertCity(city: CityDomainModel)
}
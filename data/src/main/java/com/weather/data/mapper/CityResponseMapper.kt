package com.weather.data.mapper

import com.weather.data.model.CitySearchResponse
import com.weather.domain.model.CitySearchResponseDomainModel


fun CitySearchResponse.toCityResponseDomainModel() = CitySearchResponseDomainModel(
    name = name ?: "",
    latitude = latitude ?: 0.0,
    longitude = longitude ?: 0.0,
    country = country ?: "",
    state = state ?: ""
)
package com.weather.city.mapper

import com.weather.domain.model.CityDomainModel
import com.weather.domain.model.CitySearchResponseDomainModel

fun CitySearchResponseDomainModel.toCityDomainModel() = CityDomainModel(
    cityName = "$name, $state, $country" , cityLat = latitude , cityLong = longitude
)
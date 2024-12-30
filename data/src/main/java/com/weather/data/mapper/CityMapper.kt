package com.weather.data.mapper

import com.weather.data.model.CityEntity
import com.weather.domain.model.CityDomainModel


fun CityEntity.toCityDomainModel() = CityDomainModel(
    cityName = cityName, cityLat = cityLat, cityLong = cityLong
)

fun CityDomainModel.toCityEntity() = CityEntity(
    cityName = cityName, cityLat = cityLat, cityLong = cityLong
)
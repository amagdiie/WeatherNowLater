package com.weather.domain.usecase

import com.weather.domain.repository.WeatherRepositoryInterface
import javax.inject.Inject

class SearchForCityByNameUseCae @Inject constructor(private val repo: WeatherRepositoryInterface) {
    suspend fun execute(citName: String) = repo.searchForCityByName(citName)
}
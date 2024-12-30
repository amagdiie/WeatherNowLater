package com.weather.domain.usecase

import com.weather.domain.model.CityDomainModel
import com.weather.domain.repository.CityRepositoryInterface
import javax.inject.Inject

class InsertCityUseCase @Inject constructor(private val repo: CityRepositoryInterface) {
    suspend fun execute(city: CityDomainModel) = repo.insertCity(city)
}
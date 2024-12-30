package com.weather.domain.usecase

import com.weather.domain.repository.CityRepositoryInterface
import javax.inject.Inject

class GetAllCitiesUseCase @Inject constructor(private val repo: CityRepositoryInterface) {
    suspend fun execute() = repo.getAllCities()
}
package com.weather.data.repository

import android.util.Log
import com.weather.core.DataHolder
import com.weather.data.local.LocalSourceInterface
import com.weather.data.mapper.toCityDomainModel
import com.weather.data.mapper.toCityEntity
import com.weather.domain.model.CityDomainModel
import com.weather.domain.repository.CityRepositoryInterface
import javax.inject.Inject

class CityRepositoryImp @Inject constructor(
    private val localSourceInterface: LocalSourceInterface
) : CityRepositoryInterface {
    override suspend fun getAllCities(): DataHolder<List<CityDomainModel>> {
        return try {
            val cityEntities = localSourceInterface.getAllCities()
            val cityDomainModels = cityEntities.map { it.toCityDomainModel() }
            DataHolder.Success(cityDomainModels)
        } catch (e: Exception) {
            Log.e("okhttp", "getAllCities: ${e.message}")
            DataHolder.Failure(e.message ?: "Unknown error")
        }
    }

    override suspend fun insertCity(city: CityDomainModel) {
        localSourceInterface.insertCities(city.toCityEntity())
    }
}
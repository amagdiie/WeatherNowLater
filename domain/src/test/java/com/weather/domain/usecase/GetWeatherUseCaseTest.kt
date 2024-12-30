package com.weather.domain.usecase

import com.weather.core.DataHolder
import com.weather.domain.model.WeatherDomainModel
import com.weather.domain.repository.WeatherRepositoryInterface
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class GetWeatherUseCaseTest {

    private lateinit var repositoryInterface: WeatherRepositoryInterface
    private lateinit var getWeatherUseCase: GetWeatherUseCase

    @Before
    fun setUp() {
        repositoryInterface = mockk()
        getWeatherUseCase = GetWeatherUseCase(repositoryInterface)
    }

    @Test
    fun `execute should return weather`()=runBlocking {
        //GIVEN
        val expectedWeather = WeatherDomainModel(
            "",
            0.0,
            0.0,
            0,
            0,
            0.0,
            0.0,
            "",
            "",
            listOf(
                WeatherDomainModel.DailyWeather(0.0, 0.0, "", 0.0, "")
            )
        )
        coEvery { getWeatherUseCase.execute(0.0,0.0) } returns DataHolder.Success(expectedWeather)

        //WHEN
        val result = getWeatherUseCase.execute(0.0,0.0)

        //THEN
        assertEquals(expectedWeather,result.data)
    }
}
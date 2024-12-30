package com.weather.nowlater.di

import android.content.Context
import androidx.room.Room
import com.weather.data.local.CityDao
import com.weather.data.local.CityDataBase
import com.weather.data.local.LocalSourceImp
import com.weather.data.local.LocalSourceInterface
import com.weather.data.remote.RemoteSourceImp
import com.weather.data.remote.RemoteSourceInterface
import com.weather.data.remote.Services
import com.weather.data.repository.CityRepositoryImp
import com.weather.data.repository.WeatherRepositoryImp
import com.weather.domain.repository.CityRepositoryInterface
import com.weather.domain.repository.WeatherRepositoryInterface
import com.weather.domain.usecase.GetAllCitiesUseCase
import com.weather.domain.usecase.GetWeatherUseCase
import com.weather.domain.usecase.InsertCityUseCase
import com.weather.domain.usecase.SearchForCityByNameUseCae
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BASIC
    )

    const val provideUrl =
        "https://api.openweathermap.org/data/3.0/"

    @Provides
    @Singleton
    fun provideCityDatabase(@ApplicationContext context: Context): CityDataBase =
        Room.databaseBuilder(context, CityDataBase::class.java, "Database").build()

    @Provides
    @Singleton
    fun provideCityDao(dataBase: CityDataBase): CityDao = dataBase.getCityDao()

    @Provides
    @Singleton
    fun provideLocalSourceImp(universityDAO: CityDao): LocalSourceInterface {
        return LocalSourceImp(universityDAO)
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(logging).connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS).build()
    }

    @Provides
    @Singleton
    fun provideApi(okHttpClient: OkHttpClient): Services {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient).baseUrl(provideUrl).build().create(Services::class.java)
    }

    @Provides
    @Singleton
    fun provideRemoteSource(services: Services): RemoteSourceInterface =
        RemoteSourceImp(services)

    @Provides
    @Singleton
    fun provideWeatherRepository(
        remoteSourceInterface: RemoteSourceInterface
    ): WeatherRepositoryInterface =
        WeatherRepositoryImp(remoteSourceInterface)

    @Provides
    @Singleton
    fun provideCityRepository(
        localSourceInterface: LocalSourceInterface
    ): CityRepositoryInterface =
        CityRepositoryImp(localSourceInterface)

    @Provides
    @Singleton
    fun provideGetWeatherUseCase(repository: WeatherRepositoryInterface): GetWeatherUseCase =
        GetWeatherUseCase(repository)

    @Provides
    @Singleton
    fun provideGetCityInfoUseCase(repository: WeatherRepositoryInterface): SearchForCityByNameUseCae =
        SearchForCityByNameUseCae(repository)

    @Provides
    @Singleton
    fun provideGetCityUseCase(repository: CityRepositoryInterface): GetAllCitiesUseCase =
        GetAllCitiesUseCase(repository)

    @Provides
    @Singleton
    fun provideInsertCityUseCase(repository: CityRepositoryInterface): InsertCityUseCase =
        InsertCityUseCase(repository)

}
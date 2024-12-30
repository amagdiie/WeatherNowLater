package com.current.weather.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.current.weather.state.GetWeatherState
import com.weather.core.DataHolder
import com.weather.domain.usecase.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(private val getWeatherUseCase: GetWeatherUseCase) :
    ViewModel() {
    private val _stateGetWeather: MutableStateFlow<GetWeatherState.Display> =
        MutableStateFlow(GetWeatherState.Display())
    val stateGetWeather = _stateGetWeather.asStateFlow()
    private val _getWeatherErrorState: MutableSharedFlow<GetWeatherState.Failure> =
        MutableSharedFlow()
    val getWeatherErrorState = _getWeatherErrorState.asSharedFlow()

    fun getWeather(lat: Double, lon: Double) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = getWeatherUseCase.execute(lat, lon)) {
                is DataHolder.Failure -> {
                    _getWeatherErrorState.emit(GetWeatherState.Failure(result.error ?: ""))
                }

                is DataHolder.Success -> {
                    result.data?.let {
                        _stateGetWeather.value =
                            GetWeatherState.Display(it, loading = false)
                    }
                }
            }
        }
    }
}
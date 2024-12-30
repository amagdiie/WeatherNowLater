package com.weather.dayforecast.view_model

sealed class DaysForecastIntent {
    data object Load : DaysForecastIntent()
}
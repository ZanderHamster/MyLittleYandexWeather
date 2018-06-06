package com.android.lekveishvili.mylittleyandexweather.model

import com.google.gson.annotations.SerializedName

data class CurrentWeatherCities(
        @SerializedName("cnt") val count: Int = 0,
        @SerializedName("list") val list: List<CurrentWeather> = emptyList()
)
package com.android.lekveishvili.mylittleyandexweather.data.model

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
        @SerializedName("main") val main: WeatherMain = WeatherMain(),
        @SerializedName("weather") val weather: List<Weather> = emptyList(),
        @SerializedName("id") val id: Int = 0,
        @SerializedName("name") val name: String = ""

)


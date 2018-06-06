package com.android.lekveishvili.mylittleyandexweather.model

import com.google.gson.annotations.SerializedName

data class City(
        @SerializedName("id") val id: Int = 0,
        @SerializedName("name") val name: String = "",
        @SerializedName("count") val count: Int = 0,
        @SerializedName("main") val main: WeatherMain = WeatherMain(),
        @SerializedName("weather") val weather: List<Weather> = emptyList()
)
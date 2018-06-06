package com.android.lekveishvili.mylittleyandexweather.model

import com.google.gson.annotations.SerializedName

data class FindCitiesId(
        @SerializedName("message") val message: String = "",
        @SerializedName("cod") val cod: String = "",
        @SerializedName("count") val count: Int = 0,
        @SerializedName("main") val main: WeatherMain = WeatherMain(),
        @SerializedName("list") val cities: List<City> = emptyList()
)
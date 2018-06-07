package com.android.lekveishvili.mylittleyandexweather.data.model

import com.google.gson.annotations.SerializedName

data class City(
        @SerializedName("id") val id: Int = 0,
        @SerializedName("name") val name: String = "",
        @SerializedName("count") val count: Int = 0,
        @SerializedName("main") val main: WeatherMain = WeatherMain(),
        @SerializedName("weather") val weather: List<Weather> = emptyList(),
        @SerializedName("coord") val coord: Coord = Coord(),
        @SerializedName("sys") val sys: Sys = Sys()
) {
    data class Coord(
            @SerializedName("lat") val lat: Double = 0.0,
            @SerializedName("lon") val lon: Double = 0.0
    )

    data class Sys(
            @SerializedName("country") val country: String = ""
    )
}
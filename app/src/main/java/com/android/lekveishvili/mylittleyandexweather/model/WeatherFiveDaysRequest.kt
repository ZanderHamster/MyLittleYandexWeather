package com.android.lekveishvili.mylittleyandexweather.model

import com.google.gson.annotations.SerializedName

data class WeatherFiveDaysRequest(
        @SerializedName("cod") val cod: String = "",
        @SerializedName("message") val message: Double = 0.0,
        @SerializedName("cnt") val cnt: Int = 0,
        @SerializedName("list") val list: List<WeatherFiveDayItem> = emptyList(),
        @SerializedName("city") val city: City = WeatherFiveDaysRequest.City()
) {

    fun getFirstDay() = list[0]

    fun getSecondDay() = list.reversed()[18]

    fun getThirdDay() = list.reversed()[10]

    data class WeatherFiveDayItem(
            @SerializedName("dt") val dt: Long = 0,
            @SerializedName("dt_txt") val dtStr: String = "",
            @SerializedName("main") val main: WeatherMain = WeatherMain(),
            @SerializedName("weather") val weather: List<Weather> = emptyList())

    data class Weather(
            @SerializedName("id") val id: Int = 0,
            @SerializedName("description") val description: String = ""
    )

    data class City(
            @SerializedName("id") val id: Int = 0,
            @SerializedName("name") val name: String = ""
    )
}
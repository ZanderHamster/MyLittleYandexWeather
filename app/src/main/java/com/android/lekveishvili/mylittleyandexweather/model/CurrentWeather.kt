package com.android.lekveishvili.mylittleyandexweather.model

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
        @SerializedName("main") val main: WeatherMain = WeatherMain()

) {


}


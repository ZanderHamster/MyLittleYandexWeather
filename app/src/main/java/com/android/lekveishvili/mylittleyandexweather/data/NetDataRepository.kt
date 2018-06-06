package com.android.lekveishvili.mylittleyandexweather.data

import com.android.lekveishvili.mylittleyandexweather.model.CurrentWeather
import com.android.lekveishvili.mylittleyandexweather.model.CurrentWeatherCities
import com.android.lekveishvili.mylittleyandexweather.model.FindCitiesId
import com.android.lekveishvili.mylittleyandexweather.model.WeatherFiveDaysRequest
import io.reactivex.Single

class NetDataRepository(private val netDataApi: NetDataApi) {
    companion object {
        const val API_KEY = "7103f6e02f1aefa4663439a9c5856970"
        const val lang = "ru"
        const val metric = "metric"
    }

    fun getCurrentWeatherByCity(city: String): Single<CurrentWeather> {
        return netDataApi.getCurrentWeather(city, API_KEY, lang, metric)
    }

    fun getCurrentWeatherGroup(ids: String): Single<CurrentWeatherCities> {
        return netDataApi.getCurrentWeatherCities(ids, API_KEY, lang, metric)
    }

    fun getCityByName(name: String): Single<FindCitiesId> {
        return netDataApi.getCityByName(name, API_KEY, lang, metric)
    }

    fun getWeatherForFiveDays(id: String): Single<WeatherFiveDaysRequest> {
        return netDataApi.getWeatherForFiveDays(id, API_KEY, lang, metric)
    }
}
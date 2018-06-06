package com.android.lekveishvili.mylittleyandexweather.data

import com.android.lekveishvili.mylittleyandexweather.model.CurrentWeather
import com.android.lekveishvili.mylittleyandexweather.model.CurrentWeatherCities
import com.android.lekveishvili.mylittleyandexweather.model.FindCitiesId
import com.android.lekveishvili.mylittleyandexweather.model.WeatherFiveDaysRequest
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NetDataApi {

    @GET("data/2.5/weather")
    fun getCurrentWeather(@Query("q") city: String,
                          @Query("appid") appid: String,
                          @Query("lang") lang: String,
                          @Query("units") metric: String = "metric"): Single<CurrentWeather>

    @GET("data/2.5/group")
    fun getCurrentWeatherCities(@Query("id") cities: String,
                                @Query("appid") appid: String,
                                @Query("lang") lang: String,
                                @Query("units") metric: String): Single<CurrentWeatherCities>

    @GET("data/2.5/find")
    fun getCityByName(@Query("q") city: String,
                      @Query("appid") appid: String,
                      @Query("lang") lang: String,
                      @Query("units") metric: String): Single<FindCitiesId>

    @GET("data/2.5/forecast")
    fun getWeatherForFiveDays(@Query("id") id: String,
                       @Query("appid") appid: String,
                       @Query("lang") lang: String,
                       @Query("units") metric: String): Single<WeatherFiveDaysRequest>
}
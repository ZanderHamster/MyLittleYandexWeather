package com.android.lekveishvili.mylittleyandexweather

interface Router {
    fun openMain()
    fun openSearch()
    fun openCity(cityId: String)
    fun addCity(cityId: Int)
    fun removeCity(cityId: String)
}
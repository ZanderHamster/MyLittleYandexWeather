package com.android.lekveishvili.mylittleyandexweather

interface Router {
    fun openMain()
    fun openSearch()
    fun openCity(cityId: String)
}
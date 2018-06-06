package com.android.lekveishvili.mylittleyandexweather

import android.app.Application
import com.android.lekveishvili.mylittleyandexweather.di.AppComponent
import com.android.lekveishvili.mylittleyandexweather.di.AppModule
import com.android.lekveishvili.mylittleyandexweather.di.DaggerAppComponent
import com.android.lekveishvili.mylittleyandexweather.di.NetModule

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule("https://api.openweathermap.org/"))
                .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}
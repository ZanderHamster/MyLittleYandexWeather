package com.android.lekveishvili.mylittleyandexweather.di

import com.android.lekveishvili.mylittleyandexweather.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetModule::class)])
interface AppComponent {
    fun inject(activity: MainActivity)
}
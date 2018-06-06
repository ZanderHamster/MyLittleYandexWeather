package com.android.lekveishvili.mylittleyandexweather.di

import com.android.lekveishvili.mylittleyandexweather.MainActivity
import com.android.lekveishvili.mylittleyandexweather.fragments.CityFragment
import com.android.lekveishvili.mylittleyandexweather.fragments.MainFragment
import com.android.lekveishvili.mylittleyandexweather.fragments.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetModule::class), (AppModule::class)])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: MainFragment)
    fun inject(fragment: CityFragment)
    fun inject(fragment: SearchFragment)
}
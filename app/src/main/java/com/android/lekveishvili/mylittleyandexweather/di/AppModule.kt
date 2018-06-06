package com.android.lekveishvili.mylittleyandexweather.di

import android.content.Context
import com.android.lekveishvili.mylittleyandexweather.data.NetDataApi
import com.android.lekveishvili.mylittleyandexweather.data.NetDataRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun providedataRepository(netApi: NetDataApi): NetDataRepository {
        return NetDataRepository(netApi)
    }
}
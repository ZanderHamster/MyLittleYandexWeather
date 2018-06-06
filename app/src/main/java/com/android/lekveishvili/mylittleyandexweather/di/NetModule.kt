package com.android.lekveishvili.mylittleyandexweather.di

import android.content.Context
import com.android.lekveishvili.mylittleyandexweather.BuildConfig
import com.android.lekveishvili.mylittleyandexweather.data.NetDataApi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import javax.inject.Singleton


@Module
class NetModule(private val baseUrl: String) {
    @Provides
    @Singleton
    internal fun provideCache(context: Context): Cache {
        val cacheDir = File(context.cacheDir, UUID.randomUUID().toString())
        return Cache(cacheDir, 30L * 1024 * 1024)
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(cache: Cache): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    internal fun provideDataApi(retrofit: Retrofit): NetDataApi {
        return retrofit.create(NetDataApi::class.java)
    }
}
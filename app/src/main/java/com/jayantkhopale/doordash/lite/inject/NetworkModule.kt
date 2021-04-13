package com.jayantkhopale.doordash.lite.inject

import com.jayantkhopale.doordash.lite.BuildConfig
import com.jayantkhopale.doordash.lite.api.StoresService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideStoresService(okHttpClient: OkHttpClient): StoresService {
        return Retrofit.Builder()
            .baseUrl("https://api.doordash.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.callTimeout(4000, TimeUnit.MILLISECONDS)
        if (BuildConfig.DEBUG) {
            okHttpBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return okHttpBuilder.build()
    }
}
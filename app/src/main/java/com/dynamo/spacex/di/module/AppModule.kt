package com.dynamo.spacex.di.module

import com.dynamo.spacex.BuildConfig
import com.dynamo.spacex.data.BASE_API_URL
import com.dynamo.spacex.data.network.LaunchesService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okHttpClient)
            .build()

    }

    @Provides
    @Singleton
    internal fun provideLaunchesService(retrofit: Retrofit): LaunchesService {
        return retrofit.create(LaunchesService::class.java)
    }

    @Provides
    @Singleton
    internal fun provideCoroutineDispatcher(retrofit: Retrofit): CoroutineDispatcher {
        return Dispatchers.IO
    }

}

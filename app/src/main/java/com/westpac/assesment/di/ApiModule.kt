package com.westpac.assesment.di

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.westpac.assesment.net.api.CreditCardAPI
import com.westpac.assesment.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideBaseUrl(): String = listOf(
        Constants.API_HOST_URL, Constants.API_BASE_URL, Constants.API_VERSION
    ).joinToString(separator = "/", postfix = "/")

    @Singleton
    @Provides
    fun provideConnectionTimeout(): Long = Constants.API_CONNECTION_TIMEOUT_IN_MILLIS

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideClient(time: Long): OkHttpClient = OkHttpClient.Builder()
        // Consider the build variants here based on the environment setup.
        // e.g. Set log levels only for the DEBUG build config
        // But for this assessment I have setup a logging interceptor which will log the response body every time, which is not the practice.
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }).connectTimeout(time, TimeUnit.MILLISECONDS)
        .readTimeout(time, TimeUnit.MILLISECONDS).writeTimeout(time, TimeUnit.MILLISECONDS).retryOnConnectionFailure(true).build()

    @Singleton
    @Provides
    fun provideRetrofit(baseUrl: String, convertor: Gson, httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create(convertor)).client(httpClient).build().also { Log.i("westpac", baseUrl) }

    @Singleton
    @Provides
    fun provideCreditCardApiService(retrofit: Retrofit): CreditCardAPI = retrofit.create(CreditCardAPI::class.java)
}
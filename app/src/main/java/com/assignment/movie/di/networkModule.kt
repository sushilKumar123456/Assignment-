package com.assignment.movie.di

import com.assignment.movie.api.MovieService
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONNECT_TIMEOUT = 10L
//private const val BASE_URL = "http://www.recipepuppy.com/"
private const val BASE_URL ="http://www.omdbapi.com/"

//http://www.omdbapi.com/?type=movie&apikey=5d81e1ce&page=1&s=spa
val networkModule = module {

    single { GsonBuilder().create() }

    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
            addInterceptor(httpLoggingInterceptor)
        }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(get())
            .build()
    }

    factory { get<Retrofit>().create(MovieService::class.java) }
}
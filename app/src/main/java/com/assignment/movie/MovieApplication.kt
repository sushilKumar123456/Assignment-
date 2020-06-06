package com.assignment.movie

import android.app.Application
import com.assignment.movie.di.networkModule
import com.assignment.movie.di.repositoryModule
import com.assignment.movie.di.roomModule
import com.assignment.movie.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            printLogger() // Koin Logger
            androidContext(this@MovieApplication)
            modules(listOf(roomModule, viewModelModule, networkModule, repositoryModule))
        }
    }
}
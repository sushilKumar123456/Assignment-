package com.assignment.movie.di

import com.assignment.movie.repo.MovieRepo
import org.koin.dsl.module

val repositoryModule = module {
    factory { MovieRepo(get(), get()) }
}
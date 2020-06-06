package com.assignment.movie.di

import com.assignment.movie.persitence.AppDataBase
import org.koin.dsl.module

val roomModule = module {
    single { AppDataBase.getInstance(get()) }
    single { get<AppDataBase>().getRecipeDao() }
}
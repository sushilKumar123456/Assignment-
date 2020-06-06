package com.assignment.movie.diTest

import com.assignment.movie.persitence.MovieDao
import com.assignment.movie.repo.MovieRepo
import org.koin.dsl.module

fun repoMockedDBModule(dao: MovieDao) = module {
    factory { MovieRepo(get(), dao) }
}
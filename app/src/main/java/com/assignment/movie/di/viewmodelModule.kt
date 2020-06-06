package com.assignment.movie.di

import com.assignment.movie.viewmodel.FavouritesMovieViewModel
import com.assignment.movie.viewmodel.SearchMovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SearchMovieViewModel(get()) }
    viewModel { FavouritesMovieViewModel(get()) }
}

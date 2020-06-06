package com.assignment.movie.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.assignment.movie.base.BaseViewModel
import com.assignment.movie.persitence.MovieDB
import com.assignment.movie.repo.MovieRepo
import kotlinx.coroutines.launch

class FavouritesMovieViewModel(private val repo: MovieRepo) : BaseViewModel() {

    var recipes = MutableLiveData<List<MovieDB>>()
    var recipes1= MutableLiveData<PagedList<MovieDB>>()

    fun loadRecipesPersistence() {
        ioScope.launch {
           /* val listRetrieved?= null
            mainScope.launch {
                recipes.value = listRetrieved*/
            }
        }
    }


    fun loadRecipesPersistencebyId(query:String) {
      /*  ioScope.launch {
           // val listRetrieved = repo.getAllRecipesPersistencebyId(query)

            mainScope.launch {
              val  pagedListConfig   =
                 PagedList.Config.Builder().setEnablePlaceholders(true)
                    .setPrefetchDistance(10)
                    .setPageSize(20).build();
              //  recipes1.value = LivePagedListBuilder(listRetrieved, pagedListConfig).build().value

            }*/
        }




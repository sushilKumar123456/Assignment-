package com.assignment.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import com.assignment.movie.api.NetworkState
import com.assignment.movie.base.BaseViewModel
import com.assignment.movie.datasource.MovieDataSourceFactory
import com.assignment.movie.persitence.MovieDB
import com.assignment.movie.repo.MovieRepo
import com.assignment.movie.utils.pagedListConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchMovieViewModel(repo: MovieRepo) : BaseViewModel() {

    private val dataSource = MovieDataSourceFactory(repository = repo, scope = ioScope)


    val recipes = LivePagedListBuilder(dataSource, pagedListConfig()).build()
    val networkState: LiveData<NetworkState>? = switchMap(dataSource.source) { it.getNetworkState() }

    fun fetchRecipesByIngredients(query: String) {
        val search = query.trim()
        dataSource.updateQuery(search)
    }

    fun refreshFailedRequest() =
        dataSource.getSource()?.retryFailedQuery()

    fun refreshAllList() =
        dataSource.getSource()?.refresh()

    fun getCurrentQuery() =
        dataSource.getQuery()

    fun saveRecipePersistent(recipe: ArrayList<MovieDB>?) {
        dataSource.saveRecipePersistence(recipe)
    }
     fun getMovieDetails(){
         viewModelScope.launch(){
             withContext(Dispatchers.IO) {
                 var dtat=dataSource.getMovDetails()
               //  movieDetails.postValue(dtat)

             }
         }
     }


}
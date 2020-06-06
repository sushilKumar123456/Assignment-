package com.assignment.movie.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.assignment.movie.persitence.MovieDB
import com.assignment.movie.repo.MovieRepo
import kotlinx.coroutines.CoroutineScope

class MovieDataSourceFactory(
    private val repository: MovieRepo,
    private var query: String = "",
    private val scope: CoroutineScope
) : DataSource.Factory<Int, MovieDB>() {

    val source = MutableLiveData<MovieDataSource>()


    override fun create(): DataSource<Int, MovieDB> {
        val source = MovieDataSource(repository, query, scope)
        this.source.postValue(source)
        return source
    }

    fun getQuery() = query

    fun getSource() = source.value

    fun updateQuery(query: String) {
        this.query = query
        getSource()?.refresh()
    }

    fun saveRecipePersistence(recipe: ArrayList<MovieDB>?) {
        getSource()?.saveRecipePersistence(recipe)
    }
     fun getMovDetails()
    {
       // return getSource()?.getMovieDetails()
    }

}
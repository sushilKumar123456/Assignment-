package com.assignment.movie.repo

import com.assignment.movie.api.MovieService
import com.assignment.movie.persitence.MovieDB
import com.assignment.movie.persitence.MovieDB.Companion.mapList
import com.assignment.movie.persitence.MovieDao
import com.assignment.movie.api.SafeApiRequest


import kotlinx.coroutines.Deferred
import java.util.*
import kotlin.collections.ArrayList

class MovieRepo(private val movieService: MovieService, private val dao: MovieDao): SafeApiRequest() {
    val apikey="5d81e1ce"
     val  type="movie"
    val plot="full"
    private suspend fun searchRecipe(query: String, page: Int) =
        movieService.search(apikey,type, page,query).await()

    suspend fun searchRecipesWithPagination(query: String, page: Int): List<MovieDB> {
        if (query.isEmpty()) return listOf()

        val request = searchRecipe(query, page)
         persistence(mapList(movieList = request.items))
        return mapList(movieList = request.items)
    }
    suspend fun movieDetailss(apikey : String, polt:String ,query: String) {


    }


    suspend fun persistence(recipe: List<MovieDB>) {
        dao.insertList(recipe as ArrayList<MovieDB>)
    }

    suspend fun getAllRecipesPersistence(): List<MovieDB> {
        return dao.findAllRecipes()
    }


    suspend fun deleteRecipePersistence(recipe: MovieDB) {
        dao.delete(recipe)
    }
}
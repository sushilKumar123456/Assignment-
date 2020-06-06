package com.assignment.movie.persitence

import androidx.paging.DataSource
import androidx.room.*
import com.assignment.movie.model.Movie
import com.assignment.movie.utils.Constants

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie")
    suspend fun findAllRecipes(): List<MovieDB>

 /*   @Query("SELECT * FROM Movie WHERE title = :movieId")
    fun findRecipeById(movieId: String): ArrayList<MovieDB>*/

   @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipeDB: MovieDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(recipes: ArrayList<MovieDB>)

    @Delete
    suspend fun delete(movieDB: MovieDB)


}
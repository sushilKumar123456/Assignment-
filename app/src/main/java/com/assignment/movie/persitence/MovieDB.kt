package com.assignment.movie.persitence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.assignment.movie.model.Movie

@Entity(tableName = "Movie")
data class MovieDB(
    @PrimaryKey val title: String,
    @ColumnInfo(name = "href") val href: String?,
    @ColumnInfo(name = "ingredients") val ingredients: String?,
    @ColumnInfo(name = "thumbnail") val thumbnail: String?
) {
    companion object {
        fun map(movie: Movie): MovieDB {
            return MovieDB(
                title = movie.title.trim().capitalize(),
                href = movie.href,
                ingredients = movie.ingredients.trim().capitalize(),
                thumbnail = movie.thumbnail
            )
        }

        fun mapList(movieList: List<Movie>): List<MovieDB> {
            val recipePostDBList = mutableListOf<MovieDB>()
            for (recipe in movieList) {
                recipePostDBList.add(map(recipe))
            }
            return recipePostDBList
        }

    }
}
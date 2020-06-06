package com.assignment.movie

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.assignment.movie.persitence.AppDataBase
import com.assignment.movie.persitence.MovieDB
import com.assignment.movie.persitence.MovieDao
import io.reactivex.internal.util.NotificationLite.getValue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DaoTest {

    private lateinit var movieDao: MovieDao
    private lateinit var db: AppDataBase
    private lateinit var recipeDB: MovieDB
    private lateinit var recipeDB2: MovieDB

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDataBase::class.java
        ).build()
        movieDao = db.getRecipeDao()
        recipeDB = MovieDB("Recipe1", "href1", "TitleTitleTitle", "BodyBodyBody")
        recipeDB2 = MovieDB("Recipe2", "href2", "TitleTitleTitle", "BodyBodyBody")
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun should_Insert_Recipe_Item() {

        runBlocking {
            movieDao.insert(recipeDB)
          /*  val recipeDBTest = getValue<MovieDB>(movieDao.findRecipeById(recipeDB.title))
            Assert.assertEquals(recipeDBTest.title, recipeDB.title)*/
        }
    }

    @Test
    @Throws(Exception::class)
    fun should_Get_All_Posts() {
        runBlocking {
            movieDao.insert(recipeDB)
            movieDao.insert(recipeDB2)
            val recipeDBTest = getValue<List<MovieDB>>(movieDao.findAllRecipes())
            Assert.assertEquals(recipeDBTest.size, 2)
        }
    }

    @Test
    @Throws(Exception::class)
    fun should_Replace_Post_Post() {
        runBlocking {
            movieDao.insert(recipeDB)
            movieDao.insert(recipeDB)
            val recipeDBTest = getValue<List<MovieDB>>(movieDao.findAllRecipes())
            Assert.assertEquals(recipeDBTest.size, 1)
        }
    }

    @Test
    @Throws(Exception::class)
    fun should_Delete_Post() {
        runBlocking {
            movieDao.insert(recipeDB)
            movieDao.delete(recipeDB)
          /*  val recipeDBTest = getValue<MovieDB>(movieDao.findRecipeById(recipeDB.title))
            Assert.assertNull(recipeDBTest)*/
        }
    }

    @Test
    @Throws(Exception::class)
    fun should_Delete_All_Data() {
        runBlocking {
            movieDao.insert(recipeDB)
            movieDao.deleteAllRecipeData()
          //  Assert.assertEquals(movieDao.getRecipesCount(), 0)
        }
    }
}
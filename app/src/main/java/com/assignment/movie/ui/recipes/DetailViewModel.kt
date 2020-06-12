package com.assignment.movie.ui.recipes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


class DetailViewModel : ViewModel() {
    private  var mDetails: MutableLiveData<MovieDetails?>?=null

    // TODO: Implement the ViewModel
    val apikey="5d81e1ce"
    val  type="movie"
    val plot="full"
    private var mAllUsers: LiveData<MovieDetails?>? = null


    fun getMovieDetail(id:String): LiveData<MovieDetails?>? {

        if (mDetails == null) {
            mDetails = MutableLiveData<MovieDetails?>()
            //we will load it asynchronously from server in this method
            insert(id)
        }
        return mDetails
    }

    fun insert(id: String) {
        val service = RetrofitFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.getPosts(apikey,plot,id)
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        Log.i("jhjhjhjhj",response.body().toString())
                        //Do something with response e.g show to the UI.
                        mDetails?.value =response.body()

                    } else {

                    }
                } catch (e: HttpException) {
                    ("Exception ${e.message}")
                } catch (e: Throwable) {
                    ("Ooops: Something else went wrong")
                }
            }
        }
    }
    interface RetrofitService {
        @GET(".")
        suspend fun getPosts(@Query("apikey") query: String,
                             @Query("plot") type: String,
                             @Query("t") s: String): Response<MovieDetails>

    }
    object RetrofitFactory {
        const val BASE_URL ="http://www.omdbapi.com/"


        fun makeRetrofitService(): RetrofitService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitService::class.java)
        }
    }

}
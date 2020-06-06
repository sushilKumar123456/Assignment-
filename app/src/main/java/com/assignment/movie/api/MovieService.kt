package com.assignment.movie.api

import com.assignment.movie.model.NetworkResult
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import kotlin.jvm.functions.*

class MovieDetails(val x:String) {

}

interface MovieService {
    //type=movie&apikey=5d81e1ce&page=1&s=spa

    @GET(".")
    fun search(
        @Query("apikey") query: String,
        @Query("type") type: String,
        @Query("page") page: Int,
        @Query("s") s: String

        ): Deferred<NetworkResult>

   // plot=full&apikey=5d81e1ce&t=spider


}
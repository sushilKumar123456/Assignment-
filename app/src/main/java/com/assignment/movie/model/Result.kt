package com.assignment.movie.model

import com.google.gson.annotations.SerializedName

data class NetworkResult(
    @SerializedName("Search") val items: List<Movie>
)

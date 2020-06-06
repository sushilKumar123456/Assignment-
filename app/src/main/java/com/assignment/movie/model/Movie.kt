package com.assignment.movie.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val href: String,
    @SerializedName("Type") val ingredients: String,
    @SerializedName("Poster") val thumbnail: String
)
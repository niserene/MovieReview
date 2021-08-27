package com.nishantdev961.moviereview.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.nishantdev961.moviereview.models.MovieModel

class MovieResponse {

    @SerializedName("results")
    @Expose()
    private lateinit var movie: MovieModel

    fun getMovie(): MovieModel{
        return movie
    }
}
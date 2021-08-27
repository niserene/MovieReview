package com.nishantdev961.moviereview.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.nishantdev961.moviereview.models.MovieModel

class MovieSearchResponse {

    @SerializedName("results")
    @Expose()
    var movieList: List<MovieModel>?=null;

    fun getTotal_count():Int{
        return 50;
    }

    fun getMovies(): List<MovieModel>? {
        return movieList
    }

    override fun toString(): String {
        return "MovieSearchResponse{"+
                "total count " + getTotal_count() +
                ", movies " + movieList +
                "}";
    }

}
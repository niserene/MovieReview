package com.nishantdev961.moviereview.utils

import com.nishantdev961.moviereview.models.MovieModel
import com.nishantdev961.moviereview.response.MovieSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi{

    @GET("3/search/movie")
    suspend fun searchMovie(
        @Query("api_key")key: String,
        @Query("query")query: String,
        @Query("page")page: String
    ):Response<MovieSearchResponse>


    @GET("3/movie/popular")
    suspend fun getPopular(
        @Query("api_key")key:String,
        @Query("page")page: String
    ):Response<MovieSearchResponse>


    @GET("3/movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id")movie_id: String,
        @Query("api_key")key: String
    ):Response<MovieModel>

}
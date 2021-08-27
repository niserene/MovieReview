package com.nishantdev961.moviereview.request

import com.nishantdev961.moviereview.utils.Credentials
import com.nishantdev961.moviereview.utils.MovieApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Service {


    companion object{

        private val retrofitBuilder = Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        private val retrofit: Retrofit = retrofitBuilder.build()

        private val movieApi: MovieApi = retrofit.create(MovieApi::class.java)

        fun getMovieApi():MovieApi = movieApi
    }
}
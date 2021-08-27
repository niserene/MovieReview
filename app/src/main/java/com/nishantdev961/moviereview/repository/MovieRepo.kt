package com.nishantdev961.moviereview.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nishantdev961.moviereview.models.MovieModel
import com.nishantdev961.moviereview.request.MovieApiClient
import okhttp3.OkHttpClient

class MovieRepo{

    // respositories for the MVVM architechture

    companion object{

        private var instance: MovieRepo?=null
        private lateinit var movieApiClient:MovieApiClient

        fun getInstance(): MovieRepo{
            if(instance == null){
                instance = MovieRepo()
            }
            return instance as MovieRepo
        }
    }

    init{
        movieApiClient = MovieApiClient.getInstance()
    }

    fun getMovies(): LiveData<List<MovieModel>> = movieApiClient.getMovies()

    fun searchMovieApi(query: String, page: Int){

        movieApiClient.searchMoviesApi(query, page)
    }
}
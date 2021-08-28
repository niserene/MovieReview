package com.nishantdev961.moviereview.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nishantdev961.moviereview.models.MovieModel
import com.nishantdev961.moviereview.request.MovieApiClient
import okhttp3.OkHttpClient

class MovieRepo{

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

    private  var mQuery:String="fast"
    private  var mPageNumber:Int=1
    private var mPageNumberPop = 1
    init{
        movieApiClient = MovieApiClient.getInstance()
    }

    fun getMovies(): LiveData<List<MovieModel>> = movieApiClient.getMovies()

    fun getPop(): LiveData<List<MovieModel>> = movieApiClient.getMoviesPop()

    fun searchMovieApi(query: String, page: Int){
        mQuery = query
        mPageNumber = page
        movieApiClient.searchMoviesApi(mQuery, mPageNumber)

    }

    fun searchMoviePop(page:Int){
        mPageNumber = page
        movieApiClient.searchMoviesPop(mPageNumber)
    }

    fun searchNextPage(){
        mPageNumber+=1
        searchMovieApi(mQuery, mPageNumber)
    }
    fun searchNextPagePop(){
        mPageNumberPop+=1
        searchMoviePop(mPageNumberPop)
    }
}
package com.nishantdev961.moviereview.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nishantdev961.moviereview.models.MovieModel
import com.nishantdev961.moviereview.repository.MovieRepo

class MovieListViewModel: ViewModel(){

    private lateinit var movieRepo: MovieRepo

    init{
        movieRepo = MovieRepo.getInstance()
    }

    fun getMovies(): LiveData<List<MovieModel>> = movieRepo.getMovies()

    fun getPop(): LiveData<List<MovieModel>> = movieRepo.getPop()

    fun searchMovieApi(query:String, page: Int){
        movieRepo.searchMovieApi(query, page)
    }

    fun searchMoviePop(page: Int){
        movieRepo.searchMoviePop(page)
    }

    fun searchNextPage(){
        movieRepo.searchNextPage()
    }
    fun searchNextPagePop(){
        movieRepo.searchNextPagePop()
    }
}
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

    fun searchMovieApi(query:String, page: Int){
        movieRepo.searchMovieApi(query, page)
    }
}
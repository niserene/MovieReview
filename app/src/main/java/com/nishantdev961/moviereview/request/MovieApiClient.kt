package com.nishantdev961.moviereview.request

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nishantdev961.moviereview.models.MovieModel
import com.nishantdev961.moviereview.response.MovieSearchResponse
import com.nishantdev961.moviereview.utils.Credentials
import kotlinx.coroutines.*
import retrofit2.Response

class MovieApiClient {

    private var mMovies: MutableLiveData<List<MovieModel>> = MutableLiveData()
    private var retrieveMovies: RetrieveMovies?=null

    companion object{

        private var instance: MovieApiClient?=null

        fun getInstance(): MovieApiClient{
            if(instance == null){
                instance = MovieApiClient()
            }
            return instance as MovieApiClient
        }
    }

    init{
        mMovies = MutableLiveData()
    }

    fun getMovies(): LiveData<List<MovieModel>>{
        return mMovies
    }

//    this is the method we will call from classes to get data from web source
    fun searchMoviesApi(query: String, page: Int){

        if(retrieveMovies!=null){
            retrieveMovies = null
        }
        retrieveMovies = RetrieveMovies(query, page)
        retrieveMovies!!.runIt()
    }

    inner class RetrieveMovies(private val query: String, private val page: Int) {

        //function for query

        fun runIt(){

            GlobalScope.launch (Dispatchers.Main){

                val response = async {getMovies()}.await()
                if(response.isSuccessful){

//                    Log.d("TAG", response.body()?.getMovies().toString())
                    var list : List<MovieModel> = ArrayList<MovieModel>(response.body()?.getMovies())

                    if(page == 1){

                        mMovies.postValue(list)
                    }
                    else{
                        var currentMovies: ArrayList<MovieModel> = ArrayList<MovieModel>(mMovies.value)
                        currentMovies.addAll(list)
                        mMovies.postValue(currentMovies)
                    }
                }
                else{
                    Log.d("TAG", "Error on calling retrieve movies")
                }

            }
        }

        private suspend fun getMovies():Response<MovieSearchResponse>{

            return withContext(Dispatchers.IO){
                Service.getMovieApi().searchMovie(
                        Credentials.API_KEY,
                        query,
                        page.toString()
                )
            }
        }
    }
}
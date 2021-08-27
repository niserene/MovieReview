package com.nishantdev961.moviereview


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.nishantdev961.moviereview.models.MovieModel
import com.nishantdev961.moviereview.request.Service
import com.nishantdev961.moviereview.utils.Credentials
import com.nishantdev961.moviereview.utils.MovieApi
import com.nishantdev961.moviereview.viewmodels.MovieListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MovieListActivity : AppCompatActivity(){

    // ViewModel

    lateinit var movieListViewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        movieListViewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)

        btn.setOnClickListener{

            searchMovieApi("Jack Reacher", 1)
            ObeserveTheChange()
        }
    }

    // Observing for the data change here, by LiveData

    private fun ObeserveTheChange(){

        movieListViewModel.getMovies().observe(this, Observer {movieModel->

            if(!movieModel.isNullOrEmpty()){

                for(i in 0..movieModel.size-1) {
                    movieModel[i].title?.let { Log.d("TAG", it) }
                }
            }
            else{
                Log.d("TAG", "Ye toh Khaali hai :(")
            }
        })
    }

//    Calling method in main activity
    private fun searchMovieApi(query: String, page: Int){
        movieListViewModel.searchMovieApi(query, page)
    }

//    private fun GetRetrofitResponse(){
//
//        var movieApi: MovieApi = Service.getMovieApi()
//
//        GlobalScope.launch(Dispatchers.Main) {
//            val response = withContext(Dispatchers.IO) {
//                movieApi.searchMovie(
//                    Credentials.API_KEY, "Tenet", "1"
//                )
//            }
//            if (response.isSuccessful){
//                val movies = ArrayList<MovieModel>(response.body()?.getMovies())
//                for(i in 0..movies.size-1){
//                    movies[i].title?.let { Log.d("Tag", it) }
//                }
//            }
//            else{
//                Log.d("RESP", "FAILED HO GYA")
//            }
//        }
//    }


//    private fun GetRetrofitResponseAccordingToId(){
//
//        var movieApi: MovieApi = Service.getMovieApi()
//        GlobalScope.launch(Dispatchers.Main){
//
//            val response = withContext(Dispatchers.IO){
//                movieApi.getMovie(
//                    "343611",
//                    Credentials.API_KEY
//                )
//            }
//
//            if(response.isSuccessful){
//                Log.d("TAG", response.body().toString())
//            }
//            else{
//                Log.d("TAG", "FAIL HO GYA")
//            }
//        }
//    }

}



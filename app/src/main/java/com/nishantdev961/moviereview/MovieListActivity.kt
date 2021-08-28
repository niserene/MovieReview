package com.nishantdev961.moviereview


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nishantdev961.moviereview.adapters.MovieRecyclerAdapter
import com.nishantdev961.moviereview.adapters.OnMovieListener
import com.nishantdev961.moviereview.viewmodels.MovieListViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MovieListActivity : AppCompatActivity(), OnMovieListener{


    lateinit var movieListViewModel: MovieListViewModel
    lateinit var movieRecyclerAdapter: MovieRecyclerAdapter

    var isPopular: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        setUPSearchView()

        movieListViewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)

        configRecyclerview()
        ObserveAnyChange()
        ObservePopularMovies()

        // Getting the popular movies data
        movieListViewModel.searchMoviePop(1)
    }

    // Get data from search query and output data
    private fun setUPSearchView() {

        search_view.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                movieListViewModel.searchMovieApi(
                    query, 1
                )
                return false
            }
            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }
        })
        search_view.setOnCloseListener {
            isPopular = true
            movieListViewModel.searchMoviePop(1)
            movieRecyclerAdapter.notifyDataSetChanged()
            true
        }
        search_view.setOnSearchClickListener(object: View.OnClickListener{
            override fun onClick(v: View){
                isPopular = false
            }
        })
    }
    private fun ObservePopularMovies() {

        movieListViewModel.getPop().observe(this, Observer{ movieModel ->

            if (!movieModel.isNullOrEmpty()) {
                movieRecyclerAdapter.setmMovies(movieModel)
            }
            else{
                Log.d("TAG", "boss Ye toh Khaali hai :(")
            }
        })

    }
    // Observing for the data change here, by LiveData
    private fun ObserveAnyChange() {

        movieListViewModel.getMovies().observe(this, Observer { movieModel ->

            if (!movieModel.isNullOrEmpty()) {
                movieRecyclerAdapter.setmMovies(movieModel)
            }
            else{
                Log.d("TAG", "boss Ye toh Khaali hai :(")
            }
        })
    }

    // setting up the adapter with the recycler view
    private fun configRecyclerview(){
        movieRecyclerAdapter = MovieRecyclerAdapter(this)
        recyclerView.adapter = movieRecyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if(!recyclerView.canScrollHorizontally(2)){
                    if(isPopular){
                        movieListViewModel.searchNextPagePop()
                    }
                    else{
                        movieListViewModel.searchNextPage()
                    }
                }
            }
        })
    }

    override fun onMovieClick(position: Int) {

        val intent: Intent = Intent(this, MovieDetails::class.java)
        intent.putExtra("movie", movieRecyclerAdapter.getSelectedMovie(position))
        startActivity(intent)
    }

    override fun onCategoryClick(category: String) {
        Log.d("A", "som")
    }

}



package com.nishantdev961.moviereview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nishantdev961.moviereview.R
import com.nishantdev961.moviereview.models.MovieModel

class MovieRecyclerAdapter(onMovieListener: OnMovieListener): RecyclerView.Adapter<MovieViewHolder>(){


    private  var mMovies: List<MovieModel>?=null
    private lateinit var onMovieListener: OnMovieListener

    init {
        this.onMovieListener = onMovieListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
                LayoutInflater.from(parent.context)
                        .inflate(R.layout.movie_list_item, parent, false),
                onMovieListener
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        mMovies?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int{
        if(!mMovies.isNullOrEmpty())
            return mMovies!!.size
        return 0
    }

    fun setmMovies(mMovies: List<MovieModel>){
        this.mMovies = mMovies
        notifyDataSetChanged()
    }

    fun getSelectedMovie(position: Int): MovieModel?{
        if(!mMovies.isNullOrEmpty()){
            return mMovies!!.get(position)
        }
        return null
    }
}
package com.nishantdev961.moviereview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.nishantdev961.moviereview.models.MovieModel
import com.nishantdev961.moviereview.utils.Credentials
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        getDataFromIntent()
    }

    private fun getDataFromIntent() {

        if(intent.hasExtra("movie")){
            val movieModel: MovieModel? = intent.getParcelableExtra<MovieModel>("movie")

            if (movieModel != null) {
                textView_title_details.text = movieModel.title
            }
            if (movieModel != null) {
                textView_overview_details.text = movieModel.overview
            }
            if (movieModel != null) {
                ratingBar_details.rating = (movieModel.vote_average)?.div(2)?.toFloat() ?: 0f
            }
            if (movieModel != null) {
                Glide.with(this)
                    .load(Credentials.IMG_URL + movieModel.poster_path)
                    .into(imageView_details)
            }
        }
    }
}
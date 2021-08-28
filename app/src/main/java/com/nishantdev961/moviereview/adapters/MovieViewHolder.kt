package com.nishantdev961.moviereview.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nishantdev961.moviereview.models.MovieModel
import com.nishantdev961.moviereview.utils.Credentials
import kotlinx.android.synthetic.main.movie_list_item.view.*

class MovieViewHolder(itemView: View, onMovieListener: OnMovieListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

    private lateinit var onMovieListener: OnMovieListener

    init{
        this.onMovieListener = onMovieListener
        itemView.setOnClickListener(this)
    }

    fun bind(item: MovieModel) {
        itemView.apply {
            movie_title.text = item.title
            movie_category.text = item.overview
            movie_lang.text = item.original_language.toString()
            movie_category.text = item.release_date
            movie_rating_bar.rating = (item.vote_average?.div(2))?.toFloat() ?:1f
        }
        Glide.with(itemView.context)
                .load(Credentials.IMG_URL + item.poster_path)
                .into(itemView.movie_img)
    }

    override fun onClick(view: View) {
        onMovieListener.onMovieClick(adapterPosition)
    }

}

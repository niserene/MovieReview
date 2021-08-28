package com.nishantdev961.moviereview.adapters

interface OnMovieListener {

    fun onMovieClick(position: Int)

    fun onCategoryClick(category: String)
}
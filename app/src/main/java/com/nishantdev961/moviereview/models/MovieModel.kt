package com.nishantdev961.moviereview.models

// Model class for the movie data fetched from api

data class MovieModel(

    val id: Int? = null,
    val title: String? = null,
    val release_date: String? = null,
    val poster_path: String? = null,
    val vote_average: Double? = null,
    val overview: String? = null,
)
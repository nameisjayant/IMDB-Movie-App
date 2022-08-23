package com.programming_simplified.movieapp.features.movies.ui

import com.programming_simplified.movieapp.data.model.Movies

data class MovieState(
    val data:List<Movies.Results>? = emptyList(),
    val error:String = "",
    val isLoading:Boolean = false
)

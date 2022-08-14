package com.programming_simplified.movieapp.utils

sealed class MovieNavigationItems(val route:String){

    object MovieList : MovieNavigationItems("movielist")
    object MovieDetails : MovieNavigationItems("movieDetails")

}

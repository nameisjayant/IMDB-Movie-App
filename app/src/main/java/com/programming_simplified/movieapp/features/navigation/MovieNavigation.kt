package com.programming_simplified.movieapp.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.programming_simplified.movieapp.features.movies.screens.MovieDetailScreen
import com.programming_simplified.movieapp.features.movies.screens.MovieListScreen
import com.programming_simplified.movieapp.features.movies.ui.MovieViewModel
import com.programming_simplified.movieapp.utils.MovieNavigationItems

@Composable
fun MovieNavigation(
    viewModel: MovieViewModel
) {

    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController,
        startDestination = MovieNavigationItems.MovieList.route
    ) {
        composable(MovieNavigationItems.MovieList.route){
            MovieListScreen(viewModel = viewModel, navHostController = navHostController)
        }
        composable(MovieNavigationItems.MovieDetails.route){
            MovieDetailScreen(viewModel)
        }
    }

}
package com.programming_simplified.movieapp.features.movies.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.programming_simplified.movieapp.R
import com.programming_simplified.movieapp.data.network.ApiService
import com.programming_simplified.movieapp.features.movies.ui.MovieViewModel

@Composable
fun MovieDetailScreen(
    viewModel: MovieViewModel
) {

    val response = viewModel.movieDetails.value
    Log.d("main", "MovieDetailScreen: ${response.original_title}")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {
        Image(
            painter = rememberImagePainter(
                data = "${ApiService.BASE_POSTER_URL}${response.poster_path ?: ""}",
                builder = {
                    crossfade(true)
                    placeholder(R.drawable.ic_launcher_foreground)
                }
            ),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterHorizontally).size(300.dp)
        )
        Text(text =  response.original_title ?: "-", style = MaterialTheme.typography.h6)
        Text(text = response.vote_average ?: "0", style = MaterialTheme.typography.caption)
        Text(
            text = response.overview ?: "-",
            style = MaterialTheme.typography.caption
        )

    }

}
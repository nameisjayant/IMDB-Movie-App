package com.programming_simplified.movieapp.features.movies.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.programming_simplified.movieapp.R
import com.programming_simplified.movieapp.data.model.Movies
import com.programming_simplified.movieapp.data.network.ApiService
import com.programming_simplified.movieapp.features.movies.ui.MovieViewModel
import com.programming_simplified.movieapp.utils.MovieNavigationItems


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieListScreen(
    viewModel: MovieViewModel,
    navHostController: NavHostController
) {

    val response = viewModel.movieResponse.value

    if(response.data?.isNotEmpty()!!)
        LazyColumn{
            items(response.data, key = {it.id!!}){res->
                MovieList(results = res ) {
                    viewModel.setMovie(res)
                    navHostController.navigate(MovieNavigationItems.MovieDetails.route)
                }
            }
        }

    if(response.error.isNotEmpty())
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            Text(text = response.error)
        }

    if(response.isLoading)
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }

}

@ExperimentalMaterialApi
@Composable
fun MovieList(results: Movies.Results, onGettingClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        onClick = { onGettingClick() }

    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("${ApiService.BASE_POSTER_URL}${results.poster_path}")
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .crossfade(true)
                        .transformations(CircleCropTransformation())
                        .build()
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)
                    .padding(horizontal = 10.dp, vertical = 10.dp)
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = results.original_title!!, style = typography.h6, textAlign = TextAlign.Center)
                Text(text = results.overview!!, style = typography.caption, textAlign = TextAlign.Center)

            }
        }
    }
}
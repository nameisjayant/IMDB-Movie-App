package com.programming_simplified.movieapp.features.movies.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programming_simplified.movieapp.common.doOnFailure
import com.programming_simplified.movieapp.common.doOnLoading
import com.programming_simplified.movieapp.common.doOnSuccess
import com.programming_simplified.movieapp.data.model.Movies
import com.programming_simplified.movieapp.features.movies.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
):ViewModel() {

    private val _movieResponse:MutableState<MovieState> = mutableStateOf(MovieState())
    val movieResponse:State<MovieState> = _movieResponse

    private val _movieDetails:MutableState<Movies.Results> = mutableStateOf(Movies.Results())
    val movieDetails:MutableState<Movies.Results> = _movieDetails

    fun setMovie(data:Movies.Results){
        _movieDetails.value = data
    }

    init {
        getMovies()
    }

    private fun getMovies() = viewModelScope.launch {
        movieUseCase.getMovies()
            .doOnSuccess {
                _movieResponse.value = MovieState(
                    it,
                )
            }
            .doOnFailure {
                _movieResponse.value = MovieState(
                   error = it.toString(),
                )
            }
            .doOnLoading {
                _movieResponse.value = MovieState(
                   isLoading =  true
                )
            }.collect()
    }

}


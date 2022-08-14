package com.programming_simplified.movieapp.features.movies.domain.repository

import com.programming_simplified.movieapp.common.Result
import com.programming_simplified.movieapp.data.model.Movies
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieRepository {

    suspend fun getMovies():Flow<Result<Movies>>

}
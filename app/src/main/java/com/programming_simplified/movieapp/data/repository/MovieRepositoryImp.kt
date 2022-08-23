package com.programming_simplified.movieapp.data.repository

import com.programming_simplified.movieapp.common.Result
import com.programming_simplified.movieapp.common.base.BaseRepository
import com.programming_simplified.movieapp.data.model.Movies
import com.programming_simplified.movieapp.data.network.ApiService
import com.programming_simplified.movieapp.features.movies.domain.repository.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(
    private val apiService: ApiService,
) : BaseRepository() , MovieRepository {

    override suspend fun getMovies(): Flow<Result<Movies>> = safeApiCall {
        apiService.getMoviesList()
    }
}
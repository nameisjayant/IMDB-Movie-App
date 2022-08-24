package com.programming_simplified.movieapp.features.movies.domain.usecase

import com.programming_simplified.movieapp.common.Result
import com.programming_simplified.movieapp.common.base.Mapper
import com.programming_simplified.movieapp.common.map
import com.programming_simplified.movieapp.data.model.Movies
import com.programming_simplified.movieapp.features.movies.domain.mapper.MovieMapper
import com.programming_simplified.movieapp.features.movies.domain.model.MovieUiModel
import com.programming_simplified.movieapp.features.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieMapper:MovieMapper
) {

    suspend fun getMovies(): Flow<Result<List<MovieUiModel>?>> {
        return movieRepository.getMovies().map { result->
            result.map {
                movieMapper.mapFrom(it)
            }
        }
    }

}
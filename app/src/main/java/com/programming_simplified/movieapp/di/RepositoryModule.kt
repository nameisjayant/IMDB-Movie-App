package com.programming_simplified.movieapp.di

import com.programming_simplified.movieapp.data.repository.MovieRepositoryImp
import com.programming_simplified.movieapp.features.movies.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun providesMovieRepository(
        movieRepositoryImp: MovieRepositoryImp
    ):MovieRepository


}
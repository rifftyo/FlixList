package com.rifftyo.flixlist.core.domain.usecase

import com.rifftyo.flixlist.core.data.Resource
import com.rifftyo.flixlist.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getNowPlayingMovies(): Flow<Resource<List<Movie>>>

    fun getPopularMovies(): Flow<Resource<List<Movie>>>

    fun getTopRatedMovies(): Flow<Resource<List<Movie>>>

    fun getUpcomingMovies(): Flow<Resource<List<Movie>>>

    fun getDiscoverMovies(): Flow<Resource<List<Movie>>>

    fun getAllMovies(): Flow<Resource<List<Movie>>>

    fun getSearchMovies(query: String): Flow<Resource<List<Movie>>>

    fun getDetailMovie(movieId: Int): Flow<Resource<Movie>>

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteMovie(movie: Movie, state: Boolean)
}
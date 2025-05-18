package com.rifftyo.flixlist.core.domain.usecase

import com.rifftyo.flixlist.core.domain.model.Movie
import com.rifftyo.flixlist.core.domain.repository.IMovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository): MovieUseCase {
    override fun getNowPlayingMovies() = movieRepository.getNowPlayingMovies()

    override fun getPopularMovies() = movieRepository.getPopularMovies()

    override fun getTopRatedMovies() = movieRepository.getTopRatedMovies()

    override fun getUpcomingMovies() = movieRepository.getUpcomingMovies()

    override fun getDiscoverMovies() = movieRepository.getDiscoverMovies()

    override fun getAllMovies() = movieRepository.getAllMovies()

    override fun getSearchMovies(query: String) = movieRepository.getSearchMovies(query)

    override fun getDetailMovie(movieId: Int) = movieRepository.getDetailMovie(movieId)

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)
}
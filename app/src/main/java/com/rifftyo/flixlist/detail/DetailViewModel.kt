package com.rifftyo.flixlist.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rifftyo.flixlist.core.domain.model.Movie
import com.rifftyo.flixlist.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun movieDetail(movieId: Int) = movieUseCase.getDetailMovie(movieId).asLiveData()

    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}
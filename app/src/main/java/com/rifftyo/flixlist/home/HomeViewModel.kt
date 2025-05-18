package com.rifftyo.flixlist.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rifftyo.flixlist.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {

    val nowPlayingMovies = movieUseCase.getNowPlayingMovies().asLiveData()

    val popularMovies = movieUseCase.getPopularMovies().asLiveData()

    val topRatedMovies = movieUseCase.getTopRatedMovies().asLiveData()

    val upcomingMovies = movieUseCase.getUpcomingMovies().asLiveData()
}
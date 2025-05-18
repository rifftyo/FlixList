package com.rifftyo.flixlist.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rifftyo.flixlist.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(useCase: MovieUseCase): ViewModel() {

    val discoverMovie = useCase.getDiscoverMovies().asLiveData()
}
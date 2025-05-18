package com.rifftyo.flixlist.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rifftyo.flixlist.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class BookmarkViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {

    val getFavoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()
}
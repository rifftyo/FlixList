package com.rifftyo.flixlist.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rifftyo.flixlist.core.data.Resource
import com.rifftyo.flixlist.core.domain.model.Movie
import com.rifftyo.flixlist.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    val allMovies = movieUseCase.getAllMovies().asLiveData()

    private val _queryChannel = MutableStateFlow("")
    val queryChannel: MutableStateFlow<String> = _queryChannel

    private val _searchResult = MutableStateFlow<Resource<List<Movie>>>(Resource.Success(emptyList()))
    val searchResultLiveData = _searchResult.asLiveData()

    init {
        viewModelScope.launch {
            queryChannel
                .debounce(300)
                .distinctUntilChanged()
                .collectLatest { query ->
                    if (query.isBlank()) {
                        movieUseCase.getAllMovies().collect {
                            _searchResult.value = it
                        }
                    } else {
                        movieUseCase.getSearchMovies(query).collect {
                            _searchResult.value = it
                        }
                    }
                }
        }
    }
}
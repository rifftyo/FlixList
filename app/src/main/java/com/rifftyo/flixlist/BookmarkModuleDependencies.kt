package com.rifftyo.flixlist

import com.rifftyo.flixlist.core.domain.usecase.MovieUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface BookmarkModuleDependencies {
    fun movieUseCase(): MovieUseCase
}
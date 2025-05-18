package com.rifftyo.flixlist.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val movieId: Int,
    val title: String,
    val image: String,
    val overview: String,
    val releaseDate: String,
    val genre: String,
    val rating: Double,
    var isFavorite: Boolean
) : Parcelable
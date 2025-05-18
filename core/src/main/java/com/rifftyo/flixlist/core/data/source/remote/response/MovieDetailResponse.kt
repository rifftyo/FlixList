package com.rifftyo.flixlist.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse (

    @field:SerializedName("id")
    val movieId: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("release_date")
    val release: String,

    @field:SerializedName("vote_average")
    val rating: Double,

    @field:SerializedName("poster_path")
    val poster: String,

    @field: SerializedName("overview")
    val overview: String,

    @field: SerializedName("genres")
    val genre: List<GenreMovie>
)
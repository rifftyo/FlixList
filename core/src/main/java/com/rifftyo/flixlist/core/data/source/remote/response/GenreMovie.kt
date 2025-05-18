package com.rifftyo.flixlist.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GenreMovie (
    @field:SerializedName("name")
    val genreName: String
)
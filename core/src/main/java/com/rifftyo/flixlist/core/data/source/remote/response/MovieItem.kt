package com.rifftyo.flixlist.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieItem(

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("poster_path")
	val posterPath: String?,

	@field:SerializedName("id")
	val id: Int,
)
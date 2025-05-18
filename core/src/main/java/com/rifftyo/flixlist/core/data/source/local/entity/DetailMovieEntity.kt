package com.rifftyo.flixlist.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_movie")
data class DetailMovieEntity(
    @PrimaryKey
    val movieId: Int,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("image")
    val image: String,

    @ColumnInfo("overview")
    val overview: String,

    @ColumnInfo("release_date")
    val releaseDate: String,

    @ColumnInfo("genre")
    val genre: String,

    @ColumnInfo("rating")
    val rating: Double,

    @ColumnInfo("isFavorite")
    var isFavorite: Boolean
)
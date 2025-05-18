package com.rifftyo.flixlist.core.data.source.remote.network

import com.rifftyo.flixlist.core.data.source.remote.response.MovieDetailResponse
import com.rifftyo.flixlist.core.data.source.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET ("movie/now_playing")
    suspend fun getNowPlayingMovies(): MovieResponse

    @GET ("movie/popular")
    suspend fun getPopularMovies(): MovieResponse

    @GET ("movie/top_rated")
    suspend fun getTopRatedMovies(): MovieResponse

    @GET ("movie/upcoming")
    suspend fun getUpcomingMovies(): MovieResponse

    @GET("search/movie")
    suspend fun getSearchMovies(
        @Query("query") query: String
    ): MovieResponse

    @GET("discover/movie")
    suspend fun getDiscoverMovies(): MovieResponse

    @GET("movie/{id}")
    suspend fun getDetailMovie(
        @Path("id") id: Int
    ): MovieDetailResponse
}
package com.rifftyo.flixlist.core.utils

import com.rifftyo.flixlist.core.data.source.local.entity.DetailMovieEntity
import com.rifftyo.flixlist.core.data.source.local.entity.DiscoverMovieEntity
import com.rifftyo.flixlist.core.data.source.local.entity.NowPlayingMovieEntity
import com.rifftyo.flixlist.core.data.source.local.entity.PopularMovieEntity
import com.rifftyo.flixlist.core.data.source.local.entity.TopRatedMovieEntity
import com.rifftyo.flixlist.core.data.source.local.entity.UpcomingMovieEntity
import com.rifftyo.flixlist.core.data.source.remote.response.MovieDetailResponse
import com.rifftyo.flixlist.core.data.source.remote.response.MovieItem
import com.rifftyo.flixlist.core.domain.model.Movie

object DataMapper {

    // Now Playing Movie
    fun mapNowPlayingResponseToEntities(input: List<MovieItem>): List<NowPlayingMovieEntity> {
        val nowPlayingMovieList = ArrayList<NowPlayingMovieEntity>()
        input.map {
            val nowPlayingMovie = NowPlayingMovieEntity(
                movieId = it.id,
                title = it.title,
                image = it.posterPath ?: "https://www.advancescreenings.com/img/timthumb.php?src=/img/posters/t/they_will_kill_you.jpg&h=318&w=215&q=100"
            )
            nowPlayingMovieList.add(nowPlayingMovie)
        }
        return nowPlayingMovieList
    }

    fun mapNowPlayingEntitiesToDomain(input: List<NowPlayingMovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                image = it.image,
                genre = "",
                overview = "",
                rating = 0.0,
                releaseDate = "",
                isFavorite = false
            )
        }

    // Popular Movie
    fun mapPopularResponseToEntities(input: List<MovieItem>): List<PopularMovieEntity> =
        input.map {
            PopularMovieEntity(
                movieId = it.id,
                title = it.title,
                image = it.posterPath ?: "https://www.advancescreenings.com/img/timthumb.php?src=/img/posters/t/they_will_kill_you.jpg&h=318&w=215&q=100"
            )
        }

    fun mapPopularEntitiesToDomain(input: List<PopularMovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                image = it.image,
                genre = "",
                overview = "",
                rating = 0.0,
                releaseDate = "",
                isFavorite = false
            )
        }

    // Top Rated Movie
    fun mapTopRatedResponseToEntities(input: List<MovieItem>): List<TopRatedMovieEntity> =
        input.map {
            TopRatedMovieEntity(
                movieId = it.id,
                title = it.title,
                image = it.posterPath ?: "https://www.advancescreenings.com/img/timthumb.php?src=/img/posters/t/they_will_kill_you.jpg&h=318&w=215&q=100"
            )
        }

    fun mapTopRatedEntitiesToDomain(input: List<TopRatedMovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                image = it.image,
                genre = "",
                overview = "",
                rating = 0.0,
                releaseDate = "",
                isFavorite = false
            )
        }

    // Upcoming
    fun mapUpcomingResponseToEntities(input: List<MovieItem>): List<UpcomingMovieEntity> =
        input.map {
            UpcomingMovieEntity(
                movieId = it.id,
                title = it.title,
                image = it.posterPath ?: "https://www.advancescreenings.com/img/timthumb.php?src=/img/posters/t/they_will_kill_you.jpg&h=318&w=215&q=100"
            )
        }

    fun mapUpcomingEntitiesToDomain(input: List<UpcomingMovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                image = it.image,
                genre = "",
                overview = "",
                rating = 0.0,
                releaseDate = "",
                isFavorite = false
            )
        }

    // Discover Movie
    fun mapDiscoverResponseToEntities(input: List<MovieItem>): List<DiscoverMovieEntity> =
        input.map {
            DiscoverMovieEntity(
                movieId = it.id,
                title = it.title,
                image = it.posterPath ?: "https://www.advancescreenings.com/img/timthumb.php?src=/img/posters/t/they_will_kill_you.jpg&h=318&w=215&q=100"
            )
        }

    fun mapDiscoverEntitiesToDomain(input: List<DiscoverMovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                image = it.image,
                genre = "",
                overview = "",
                rating = 0.0,
                releaseDate = "",
                isFavorite = false
            )
        }

    // Search
    fun mapSearchResponseToDomain(input: List<MovieItem>): List<Movie> =
        input.map {
            if (it.posterPath != null) {
                Movie(
                    movieId = it.id,
                    title = it.title,
                    image = it.posterPath,
                    genre = "",
                    overview = "",
                    rating = 0.0,
                    releaseDate = "",
                    isFavorite = false
                )
            } else {
                Movie(
                    movieId = it.id,
                    title = it.title,
                    image = "https://www.advancescreenings.com/img/timthumb.php?src=/img/posters/t/they_will_kill_you.jpg&h=318&w=215&q=100",
                    genre = "",
                    overview = "",
                    rating = 0.0,
                    releaseDate = "",
                    isFavorite = false
                )
            }
        }

    // Detail Movie
    fun mapDetailResponseToDomain(input: MovieDetailResponse): Movie =
        Movie(
            movieId = input.movieId,
            title = input.title,
            image = input.poster,
            rating = input.rating,
            releaseDate = input.release,
            overview = input.overview,
            genre = input.genre.joinToString(", ") { it.genreName },
            isFavorite = false
        )


    fun mapDetailResponseToEntities(input: List<MovieDetailResponse>): List<DetailMovieEntity> =
        input.map {
            DetailMovieEntity(
                movieId = it.movieId,
                title = it.title,
                image = it.poster,
                overview = it.overview,
                releaseDate = it.release,
                genre = it.genre.joinToString(", ") { genreItem -> genreItem.genreName },
                rating = it.rating,
                isFavorite = false,
            )
        }

    fun mapDetailDomainToEntity(input: Movie): DetailMovieEntity =
        DetailMovieEntity(
            movieId = input.movieId,
            title = input.title,
            image = input.image,
            overview = input.overview,
            releaseDate = input.releaseDate,
            genre = input.genre,
            rating = input.rating,
            isFavorite = input.isFavorite
        )

    fun mapDetailEntitiesToDomain(input: List<DetailMovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                title = it.title,
                image = it.image,
                genre = it.genre,
                overview = it.overview,
                rating = it.rating,
                releaseDate = it.releaseDate,
                isFavorite = it.isFavorite
            )
        }

    fun mapDetailEntitiesToDomainSingle(input: DetailMovieEntity): Movie =
        Movie(
            movieId = input.movieId,
            title = input.title,
            image = input.image,
            genre = input.genre,
            overview = input.overview,
            rating = input.rating,
            releaseDate = input.releaseDate,
            isFavorite = input.isFavorite
        )
}
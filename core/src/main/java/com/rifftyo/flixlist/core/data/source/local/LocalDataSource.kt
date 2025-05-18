package com.rifftyo.flixlist.core.data.source.local

import com.rifftyo.flixlist.core.data.source.local.entity.DetailMovieEntity
import com.rifftyo.flixlist.core.data.source.local.entity.DiscoverMovieEntity
import com.rifftyo.flixlist.core.data.source.local.entity.NowPlayingMovieEntity
import com.rifftyo.flixlist.core.data.source.local.entity.PopularMovieEntity
import com.rifftyo.flixlist.core.data.source.local.entity.TopRatedMovieEntity
import com.rifftyo.flixlist.core.data.source.local.entity.UpcomingMovieEntity
import com.rifftyo.flixlist.core.data.source.local.room.DetailMovieDao
import com.rifftyo.flixlist.core.data.source.local.room.DiscoverMovieDao
import com.rifftyo.flixlist.core.data.source.local.room.NowPlayingMovieDao
import com.rifftyo.flixlist.core.data.source.local.room.PopularMovieDao
import com.rifftyo.flixlist.core.data.source.local.room.TopRatedMovieDao
import com.rifftyo.flixlist.core.data.source.local.room.UpcomingMovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val nowPlayingMovieDao: NowPlayingMovieDao, private val popularMovieDao: PopularMovieDao, private val topRatedMovieDao: TopRatedMovieDao, private val upcomingMovieDao: UpcomingMovieDao, private val detailMovieDao: DetailMovieDao, private val discoverMovieDao: DiscoverMovieDao) {

    // Now Playing Movies
    fun getNowPlayingMovies(): Flow<List<NowPlayingMovieEntity>> = nowPlayingMovieDao.getNowPlayingMovies()
    suspend fun insertNowPlayingMovies(nowPlayingMovieList: List<NowPlayingMovieEntity>) = nowPlayingMovieDao.insertNowPlayingMovies(nowPlayingMovieList)

    // Popular Movies
    fun getPopularMovies(): Flow<List<PopularMovieEntity>> = popularMovieDao.getPopularMovies()
    suspend fun insertPopularMovies(popularMovieList: List<PopularMovieEntity>) = popularMovieDao.insertPopularMovies(popularMovieList)

    // Top Rated Movies
    fun getTopRatedMovies(): Flow<List<TopRatedMovieEntity>> = topRatedMovieDao.getTopRatedMovies()
    suspend fun insertTopRatedMovies(topRatedMovieList: List<TopRatedMovieEntity>) = topRatedMovieDao.insertTopRatedMovies(topRatedMovieList)

    // Upcoming Movies
    fun getUpcomingMovies(): Flow<List<UpcomingMovieEntity>> = upcomingMovieDao.getUpcomingMovies()
    suspend fun insertUpcomingMovies(upcomingMovieList: List<UpcomingMovieEntity>) = upcomingMovieDao.insertUpcomingMovies(upcomingMovieList)

    // Discover Movies
    fun getDiscoverMovies(): Flow<List<DiscoverMovieEntity>> = discoverMovieDao.getDiscoverMovies()
    suspend fun insertDiscoverMovies(discoverMovieList: List<DiscoverMovieEntity>) = discoverMovieDao.insertDiscoverMovies(discoverMovieList)

    // Detail Movies
    fun getFavoriteMovie(): Flow<List<DetailMovieEntity>> = detailMovieDao.getFavoriteMovie()
    fun getDetailMovie(movieId: Int): Flow<DetailMovieEntity> = detailMovieDao.getDetailMovie(movieId)
    suspend fun insertDetailMovie(movieList: List<DetailMovieEntity>) = detailMovieDao.insertDetailMovie(movieList)
    fun setFavoriteMovie(movie: DetailMovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        detailMovieDao.updateFavoriteMovie(movie)
    }
}
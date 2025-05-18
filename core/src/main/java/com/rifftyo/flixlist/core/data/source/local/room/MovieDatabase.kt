package com.rifftyo.flixlist.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rifftyo.flixlist.core.data.source.local.entity.DetailMovieEntity
import com.rifftyo.flixlist.core.data.source.local.entity.DiscoverMovieEntity
import com.rifftyo.flixlist.core.data.source.local.entity.NowPlayingMovieEntity
import com.rifftyo.flixlist.core.data.source.local.entity.PopularMovieEntity
import com.rifftyo.flixlist.core.data.source.local.entity.TopRatedMovieEntity
import com.rifftyo.flixlist.core.data.source.local.entity.UpcomingMovieEntity

@Database(entities = [NowPlayingMovieEntity::class, PopularMovieEntity::class, TopRatedMovieEntity::class, UpcomingMovieEntity::class, DetailMovieEntity::class, DiscoverMovieEntity::class], version = 3, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun nowPlayingMovieDao(): NowPlayingMovieDao

    abstract fun popularMovieDao(): PopularMovieDao

    abstract fun topRatedMovieDao(): TopRatedMovieDao

    abstract fun upcomingMovieDao(): UpcomingMovieDao

    abstract fun detailMovieDao(): DetailMovieDao

    abstract fun discoverMovieDao(): DiscoverMovieDao
}
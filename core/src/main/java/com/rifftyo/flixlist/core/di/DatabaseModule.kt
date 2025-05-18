package com.rifftyo.flixlist.core.di

import android.content.Context
import androidx.room.Room
import com.rifftyo.flixlist.core.data.source.local.room.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase = Room.databaseBuilder(
        context,
        MovieDatabase::class.java, "Movie.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideMovieDao(database: MovieDatabase) = database.nowPlayingMovieDao()

    @Provides
    fun providePopularMovieDao(database: MovieDatabase) = database.popularMovieDao()

    @Provides
    fun provideTopRatedMovieDao(database: MovieDatabase) = database.topRatedMovieDao()

    @Provides
    fun provideDiscoverMovieDao(database: MovieDatabase) = database.discoverMovieDao()

    @Provides
    fun provideUpcomingMovieDao(database: MovieDatabase) = database.upcomingMovieDao()

    @Provides
    fun provideDetailMovieDao(database: MovieDatabase) = database.detailMovieDao()
}
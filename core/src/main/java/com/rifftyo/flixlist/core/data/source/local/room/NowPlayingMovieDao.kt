package com.rifftyo.flixlist.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rifftyo.flixlist.core.data.source.local.entity.NowPlayingMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NowPlayingMovieDao {

    @Query("SELECT * FROM now_playing_movie")
    fun getNowPlayingMovies(): Flow<List<NowPlayingMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlayingMovies(movies: List<NowPlayingMovieEntity>)
}
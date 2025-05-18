package com.rifftyo.flixlist.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rifftyo.flixlist.core.data.source.local.entity.TopRatedMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopRatedMovieDao {
    @Query("SELECT * FROM top_rated_movie")
    fun getTopRatedMovies(): Flow<List<TopRatedMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedMovies(movies: List<TopRatedMovieEntity>)
}
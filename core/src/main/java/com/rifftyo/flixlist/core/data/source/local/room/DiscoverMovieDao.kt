package com.rifftyo.flixlist.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rifftyo.flixlist.core.data.source.local.entity.DiscoverMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DiscoverMovieDao {

    @Query("SELECT * FROM discover_movie")
    fun getDiscoverMovies(): Flow<List<DiscoverMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiscoverMovies(movies: List<DiscoverMovieEntity>)
}
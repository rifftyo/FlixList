package com.rifftyo.flixlist.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rifftyo.flixlist.core.data.source.local.entity.DetailMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailMovieDao {

    @Query("SELECT * FROM detail_movie where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<DetailMovieEntity>>

    @Query("SELECT * FROM detail_movie WHERE movieId = :movieId")
    fun getDetailMovie(movieId: Int): Flow<DetailMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailMovie(movies: List<DetailMovieEntity>)

    @Update
    fun updateFavoriteMovie(movies: DetailMovieEntity)
}
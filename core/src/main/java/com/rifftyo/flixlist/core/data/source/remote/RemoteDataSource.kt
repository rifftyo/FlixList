package com.rifftyo.flixlist.core.data.source.remote

import android.util.Log
import com.rifftyo.flixlist.core.data.source.remote.network.ApiResponse
import com.rifftyo.flixlist.core.data.source.remote.network.ApiService
import com.rifftyo.flixlist.core.data.source.remote.response.MovieDetailResponse
import com.rifftyo.flixlist.core.data.source.remote.response.MovieItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getNowPlayingMovies(): Flow<ApiResponse<List<MovieItem>>> {
        return safeApiCall("getNowPlayingMovies") {
            apiService.getNowPlayingMovies().results
        }
    }

    suspend fun getPopularMovies(): Flow<ApiResponse<List<MovieItem>>> {
        return safeApiCall("getPopularMovies") {
            apiService.getPopularMovies().results
        }
    }

    suspend fun getTopRatedMovies(): Flow<ApiResponse<List<MovieItem>>> {
        return safeApiCall("getTopRatedMovies") {
            apiService.getTopRatedMovies().results
        }
    }

    suspend fun getUpcomingMovies(): Flow<ApiResponse<List<MovieItem>>> {
        return safeApiCall("getUpcomingMovies") {
            apiService.getUpcomingMovies().results
        }
    }

    suspend fun getSearchMovies(query: String): Flow<ApiResponse<List<MovieItem>>> {
        return safeApiCall("getSearchMovies") {
            apiService.getSearchMovies(query).results
        }
    }

    suspend fun getDiscoverMovies(): Flow<ApiResponse<List<MovieItem>>> {
        return safeApiCall("getDiscoverMovies") {
            apiService.getDiscoverMovies().results
        }
    }

    suspend fun getDetailMovie(movieId: Int): Flow<ApiResponse<MovieDetailResponse>> {
        return safeApiCall("getDetailMovie") {
            apiService.getDetailMovie(movieId)
        }
    }

    private suspend fun <T> safeApiCall(
        apiName: String,
        apiCall: suspend () -> T
    ): Flow<ApiResponse<T>> {
        return flow {
            try {
                val result = apiCall()
                emit(ApiResponse.Success(result))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", "$apiName error: ${e.message}", e)
            }
        }.flowOn(Dispatchers.IO)
    }
}
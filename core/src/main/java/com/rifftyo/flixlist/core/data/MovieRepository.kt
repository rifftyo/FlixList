package com.rifftyo.flixlist.core.data

import com.rifftyo.flixlist.core.data.source.local.LocalDataSource
import com.rifftyo.flixlist.core.data.source.remote.RemoteDataSource
import com.rifftyo.flixlist.core.data.source.remote.network.ApiResponse
import com.rifftyo.flixlist.core.data.source.remote.response.MovieItem
import com.rifftyo.flixlist.core.domain.model.Movie
import com.rifftyo.flixlist.core.domain.repository.IMovieRepository
import com.rifftyo.flixlist.core.utils.AppExecutors
import com.rifftyo.flixlist.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getNowPlayingMovies(): Flow<Resource<List<Movie>>> =
        object :
            NetworkBoundResource<List<Movie>, List<MovieItem>>() {
            override fun loadFromDB(): Flow<List<Movie>> =
                localDataSource.getNowPlayingMovies().map {
                    DataMapper.mapNowPlayingEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieItem>>> =
                remoteDataSource.getNowPlayingMovies()

            override suspend fun saveCallResult(data: List<MovieItem>) {
                val entities = DataMapper.mapNowPlayingResponseToEntities(data)
                localDataSource.insertNowPlayingMovies(entities)
            }
        }.asFlow()

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> =
        object :
            NetworkBoundResource<List<Movie>, List<MovieItem>>() {
            override fun loadFromDB(): Flow<List<Movie>> =
                localDataSource.getPopularMovies().map {
                    DataMapper.mapPopularEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieItem>>> =
                remoteDataSource.getPopularMovies()

            override suspend fun saveCallResult(data: List<MovieItem>) {
                val entities = DataMapper.mapPopularResponseToEntities(data)
                localDataSource.insertPopularMovies(entities)
            }
        }.asFlow()

    override fun getTopRatedMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieItem>>() {
            override fun loadFromDB(): Flow<List<Movie>> =
                localDataSource.getTopRatedMovies().map {
                    DataMapper.mapTopRatedEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieItem>>> =
                remoteDataSource.getTopRatedMovies()

            override suspend fun saveCallResult(data: List<MovieItem>) {
                val entities = DataMapper.mapTopRatedResponseToEntities(data)
                localDataSource.insertTopRatedMovies(entities)
            }
        }.asFlow()

    override fun getUpcomingMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieItem>>() {
            override fun loadFromDB(): Flow<List<Movie>> =
                localDataSource.getUpcomingMovies().map {
                    DataMapper.mapUpcomingEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieItem>>> =
                remoteDataSource.getUpcomingMovies()

            override suspend fun saveCallResult(data: List<MovieItem>) {
                val entities = DataMapper.mapUpcomingResponseToEntities(data)
                localDataSource.insertUpcomingMovies(entities)
            }
        }.asFlow()

    override fun getDiscoverMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieItem>>() {
            override fun loadFromDB(): Flow<List<Movie>> =
                localDataSource.getDiscoverMovies().map {
                    DataMapper.mapDiscoverEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieItem>>> =
                remoteDataSource.getDiscoverMovies()

            override suspend fun saveCallResult(data: List<MovieItem>) {
                val entities = DataMapper.mapDiscoverResponseToEntities(data)
                localDataSource.insertDiscoverMovies(entities)
            }
        }.asFlow()

    override fun getAllMovies(): Flow<Resource<List<Movie>>> {
        return combine(
            getNowPlayingMovies(),
            getPopularMovies(),
            getTopRatedMovies(),
            getUpcomingMovies()
        ) { nowPlaying, popular, topRated, upcoming ->
            val allMovies = mutableListOf<Movie>().apply {
                nowPlaying.data?.let { addAll(it) }
                popular.data?.let { addAll(it) }
                topRated.data?.let { addAll(it) }
                upcoming.data?.let { addAll(it) }
            }

            Resource.Success(allMovies)
        }
    }

    override fun getDetailMovie(movieId: Int): Flow<Resource<Movie>> = flow {
        emit(Resource.Loading())

        try {
            val localData = localDataSource.getDetailMovie(movieId).firstOrNull()

            if (localData != null) {
                emit(Resource.Success(DataMapper.mapDetailEntitiesToDomainSingle(localData)))
            } else {
                when (val apiResponse = remoteDataSource.getDetailMovie(movieId).first()) {
                    is ApiResponse.Success -> {
                        val movieDetail = apiResponse.data
                        val entity = DataMapper.mapDetailResponseToEntities(listOf(movieDetail))
                        localDataSource.insertDetailMovie(entity)
                        val domainModel = DataMapper.mapDetailResponseToDomain(movieDetail)
                        emit(Resource.Success(domainModel))
                    }

                    is ApiResponse.Empty -> {
                        emit(Resource.Error("No detail found for movie"))
                    }

                    is ApiResponse.Error -> {
                        emit(Resource.Error(apiResponse.errorMessage))
                    }
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error("An unexpected error occurred: ${e.message}"))
        }
    }


    override fun getSearchMovies(query: String): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading())

            when (val apiResponse = remoteDataSource.getSearchMovies(query).first()) {
                is ApiResponse.Success -> {
                    val movies = DataMapper.mapSearchResponseToDomain(apiResponse.data)
                    emit(Resource.Success(movies))
                }

                is ApiResponse.Empty -> {
                    emit(Resource.Success(emptyList()))
                }

                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        }
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapDetailEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapDetailDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }
}

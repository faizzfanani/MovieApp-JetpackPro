package com.faizzfanani.movieappjetpackpro.data

import androidx.lifecycle.LiveData
import com.faizzfanani.movieappjetpackpro.data.local.LocalDataSource
import com.faizzfanani.movieappjetpackpro.data.local.entity.MovieEntity
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity
import com.faizzfanani.movieappjetpackpro.data.remote.RemoteDataSource
import com.faizzfanani.movieappjetpackpro.data.remote.response.ApiResponse
import com.faizzfanani.movieappjetpackpro.data.remote.response.MovieResponse
import com.faizzfanani.movieappjetpackpro.data.remote.response.TvShowResponse
import com.faizzfanani.movieappjetpackpro.utils.AppExecutor
import com.faizzfanani.movieappjetpackpro.vo.Resource

class Repository private constructor(val appExecutor: AppExecutor, val remoteDataSource: RemoteDataSource, val localDataSource: LocalDataSource) {
    companion object {
        private var INSTANCE: Repository? = null
        fun getInstance(appExecutor: AppExecutor, remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource): Repository {
            if (INSTANCE == null) {
                INSTANCE = Repository(appExecutor, remoteDataSource, localDataSource)
            }
            return INSTANCE!!
        }
    }
    //Movies
    fun getMovieList(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutor) {
            @Suppress("UNCHECKED_CAST")
            override fun loadFromDB(): LiveData<List<MovieEntity>> {
                return localDataSource.getMovieList()
            }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean {
                return true
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getMovieList()
            }

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = data.map {
                    MovieEntity(it.id, it.title, it.overview, it.posterPath, it.backdropPath, it.releaseDate, it.voteAverage)
                }
                localDataSource.addMovies(movieList)
            }

        }.asLiveData()
    }

    fun getMovieDetails(id: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieResponse>(appExecutor) {
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getMovieDetails(id)
            }

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<MovieResponse>> {
                return remoteDataSource.getMovieDetail(id)
            }

            override fun saveCallResult(data: MovieResponse) {
                val movieEntity = MovieEntity(data.id, data.title, data.overview, data.posterPath, data.backdropPath, data.releaseDate, data.voteAverage)

                localDataSource.addMovie(movieEntity)
            }

        }.asLiveData()
    }
    //Tv Show
    fun getTvShowList(): LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutor) {
            @Suppress("UNCHECKED_CAST")
            override fun loadFromDB(): LiveData<List<TvShowEntity>> {
                return localDataSource.getTvShowList()
            }

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean {
                return true
            }

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> {
                return remoteDataSource.getTvShowList()
            }

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = data.map {
                    TvShowEntity(it.id, it.name, it.overview, it.posterPath, it.backdropPath, it.firstAiringDate, it.voteAverage)
                }
                localDataSource.addTvShows(tvShowList)
            }

        }.asLiveData()
    }

    fun getTvShowDetails(id: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShowResponse>(appExecutor) {
            override fun loadFromDB(): LiveData<TvShowEntity> {
                return localDataSource.getTvShowDetails(id)
            }

            override fun shouldFetch(data: TvShowEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<TvShowResponse>> {
                return remoteDataSource.getTvDetail(id)
            }

            override fun saveCallResult(data: TvShowResponse) {
                val tvShowEntity = TvShowEntity(data.id, data.name, data.overview, data.posterPath, data.backdropPath, data.firstAiringDate, data.voteAverage)

                localDataSource.addTvShow(tvShowEntity)
            }

        }.asLiveData()
    }
}
package com.faizzfanani.movieappjetpackpro.data.repository

import androidx.lifecycle.LiveData
import com.faizzfanani.movieappjetpackpro.data.NetworkBoundResource
import com.faizzfanani.movieappjetpackpro.data.Repository
import com.faizzfanani.movieappjetpackpro.data.local.LocalDataSource
import com.faizzfanani.movieappjetpackpro.data.local.entity.MovieEntity
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity
import com.faizzfanani.movieappjetpackpro.data.remote.RemoteDataSource
import com.faizzfanani.movieappjetpackpro.data.remote.response.ApiResponse
import com.faizzfanani.movieappjetpackpro.data.remote.response.MovieResponse
import com.faizzfanani.movieappjetpackpro.data.remote.response.TvShowResponse
import com.faizzfanani.movieappjetpackpro.utils.AppExecutor
import com.faizzfanani.movieappjetpackpro.vo.Resource

class FakeRepository(
        private var localDataSource: LocalDataSource,
        private var remoteDataSource: RemoteDataSource,
        private var appExecutor: AppExecutor,
        var movieList: List<MovieEntity> = mutableListOf(),
        var movieEntity: MovieEntity? = null,
        var tvShowList: List<TvShowEntity> = mutableListOf(),
        var tvShowEntity: TvShowEntity? = null
) : Repository{
    override fun getMovieList(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<MovieResponse>>(appExecutor){
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
                localDataSource.addMovies(movieList)
            }

        }.asLiveData()
    }

    override fun getMovieDetails(id: Int): LiveData<Resource<MovieEntity>> {
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
                localDataSource.addMovie(movieEntity!!)
            }

        }.asLiveData()
    }

    override fun getTvShowList(): LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TvShowEntity>, List<TvShowResponse>>(appExecutor){
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
                localDataSource.addTvShows(tvShowList)
            }

        }.asLiveData()
    }

    override fun getTvShowDetails(id: Int): LiveData<Resource<TvShowEntity>> {
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
                localDataSource.addTvShow(tvShowEntity!!)
            }

        }.asLiveData()
    }
}
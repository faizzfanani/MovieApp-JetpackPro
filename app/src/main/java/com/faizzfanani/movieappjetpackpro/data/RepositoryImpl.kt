package com.faizzfanani.movieappjetpackpro.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.faizzfanani.movieappjetpackpro.data.local.LocalDataSource
import com.faizzfanani.movieappjetpackpro.data.local.entity.MovieEntity
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity
import com.faizzfanani.movieappjetpackpro.data.remote.RemoteDataSource
import com.faizzfanani.movieappjetpackpro.data.remote.response.ApiResponse
import com.faizzfanani.movieappjetpackpro.data.remote.response.MovieResponse
import com.faizzfanani.movieappjetpackpro.data.remote.response.TvShowResponse
import com.faizzfanani.movieappjetpackpro.utils.AppExecutor
import com.faizzfanani.movieappjetpackpro.vo.Resource

class RepositoryImpl(
        private val appExecutor: AppExecutor,
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource) : Repository {
    companion object {
        private var INSTANCE: RepositoryImpl? = null
        fun getInstance(appExecutor: AppExecutor, remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource): Repository {
            if (INSTANCE == null) {
                INSTANCE = RepositoryImpl(appExecutor, remoteDataSource, localDataSource)
            }
            return INSTANCE!!
        }
    }
    //Movies
    override fun getMovieList(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutor) {
            @Suppress("UNCHECKED_CAST")
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(5)
                        .setPageSize(5)
                        .build()
                return LivePagedListBuilder(localDataSource.getMovieList(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
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

    override fun getMovieFavorite(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(5)
                .setPageSize(5)
                .build()
        return LivePagedListBuilder(localDataSource.getMovieFavorite(), config).build()
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
                val movieEntity = MovieEntity(data.id, data.title, data.overview, data.posterPath, data.backdropPath, data.releaseDate, data.voteAverage)

                localDataSource.addMovie(movieEntity)
            }

        }.asLiveData()
    }

    override fun updateMovieFavorite(id: Int, isFavorite: Boolean) {
        localDataSource.updateMovieFavorite(id, isFavorite)
    }

    //Tv Show
    override fun getTvShowList(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>(appExecutor) {
            @Suppress("UNCHECKED_CAST")
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(5)
                        .setPageSize(5)
                        .build()
                return LivePagedListBuilder(localDataSource.getTvShowList(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean {
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

    override fun getTvShowFavorite(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(5)
                .setPageSize(5)
                .build()
        return LivePagedListBuilder(localDataSource.getTvShowFavorite(), config).build()
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
                val tvShowEntity = TvShowEntity(data.id, data.name, data.overview, data.posterPath, data.backdropPath, data.firstAiringDate, data.voteAverage)

                localDataSource.addTvShow(tvShowEntity)
            }

        }.asLiveData()
    }

    override fun updateTvShowFavorite(id: Int, isFavorite: Boolean) {
        localDataSource.updateTvShowFavorite(id, isFavorite)
    }
}
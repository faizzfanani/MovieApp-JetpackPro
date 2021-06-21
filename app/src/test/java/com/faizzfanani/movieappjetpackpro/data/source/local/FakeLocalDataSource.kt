package com.faizzfanani.movieappjetpackpro.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.DataSource
import com.faizzfanani.movieappjetpackpro.data.local.LocalDataSource
import com.faizzfanani.movieappjetpackpro.data.local.entity.MovieEntity
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity
import com.faizzfanani.movieappjetpackpro.utils.PagedListUtil.createMockDataSourceFactory

class FakeLocalDataSource(
        private var movieList: MutableList<MovieEntity> = mutableListOf(),
        private var movieEntity: MovieEntity? = null,
        private var tvShowList: MutableList<TvShowEntity> = mutableListOf(),
        private var tvShowEntity: TvShowEntity? = null) : LocalDataSource {
    override fun addMovie(movieEntity: MovieEntity) {
        this.movieEntity = movieEntity
    }

    override fun addMovies(movies: List<MovieEntity>) {
        this.movieList = movies.toMutableList()
    }

    override fun getMovieList(): DataSource.Factory<Int, MovieEntity> {
        return createMockDataSourceFactory(movieList)
    }

    override fun getMovieFavorite(): DataSource.Factory<Int, MovieEntity> {
        return createMockDataSourceFactory(movieList)
    }

    override fun getMovieDetails(id: Int): LiveData<MovieEntity> {
        return liveData {
            if (movieEntity != null){
                val filterData = movieEntity.takeIf { it!!.id == id }
                if(filterData != null){
                    movieEntity?.let { emit(it) }
                }else{ movieEntity?.let { emit(it) }}
            }else{ movieEntity?.let { emit(it) }}
        }
    }

    override fun updateMovieFavorite(id: Int, isFavorite: Boolean) {
        if(movieEntity?.id  == id){
            movieEntity?.isFavorite = isFavorite
        }
    }

    override fun addTvShow(tvShowEntity: TvShowEntity) {
        this.tvShowEntity = tvShowEntity
    }

    override fun addTvShows(tvShows: List<TvShowEntity>) {
        this.tvShowList = tvShows.toMutableList()
    }

    override fun getTvShowList(): DataSource.Factory<Int, TvShowEntity> {
        return createMockDataSourceFactory(tvShowList)
    }

    override fun getTvShowFavorite(): DataSource.Factory<Int, TvShowEntity> {
        return createMockDataSourceFactory(tvShowList)
    }

    override fun getTvShowDetails(id: Int): LiveData<TvShowEntity> {
        return liveData {
            if (tvShowEntity != null){
                val filterData = tvShowEntity.takeIf { it!!.id == id }
                if(filterData != null){
                    tvShowEntity?.let { emit(it) }
                }else{ tvShowEntity?.let { emit(it) }}
            }else{ tvShowEntity?.let { emit(it) }}
        }
    }

    override fun updateTvShowFavorite(id: Int, isFavorite: Boolean) {
        if (tvShowEntity?.id == id){
            tvShowEntity?.isFavorite = isFavorite
        }
    }

}
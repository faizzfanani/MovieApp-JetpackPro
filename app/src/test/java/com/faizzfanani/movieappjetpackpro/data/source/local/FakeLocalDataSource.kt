package com.faizzfanani.movieappjetpackpro.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.faizzfanani.movieappjetpackpro.data.local.LocalDataSource
import com.faizzfanani.movieappjetpackpro.data.local.entity.MovieEntity
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity

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

    override fun getMovieList(): LiveData<List<MovieEntity>> {
        return liveData {
            if (movieList.isNotEmpty()){
                emit(movieList)
            }
            else if (movieList.isNullOrEmpty()){
                emit(emptyList())
            }
        }
    }

    override fun getMovieDetails(id: Int): LiveData<MovieEntity> {
        return liveData {
            if (movieEntity != null){
                val filterData = movieEntity.takeIf { it!!.id == id }
                if(filterData != null){
                    movieEntity?.let { emit(it) }
                }
            }
        }
    }

    override fun addTvShow(tvShowEntity: TvShowEntity) {
        this.tvShowEntity = tvShowEntity
    }

    override fun addTvShows(tvShows: List<TvShowEntity>) {
        this.tvShowList = tvShows.toMutableList()
    }

    override fun getTvShowList(): LiveData<List<TvShowEntity>> {
        return liveData {
            if (tvShowList.isNotEmpty()){
                emit(tvShowList)
            }else if (tvShowList.isNullOrEmpty()){
                emit(emptyList())
            }
        }
    }

    override fun getTvShowDetails(id: Int): LiveData<TvShowEntity> {
        return liveData {
            if (tvShowEntity != null){
                val filterData = tvShowEntity.takeIf { it!!.id == id }
                if(filterData != null){
                    tvShowEntity?.let { emit(it) }
                }
            }
        }
    }

}
package com.faizzfanani.movieappjetpackpro.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.faizzfanani.movieappjetpackpro.data.local.entity.MovieEntity
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity

interface LocalDataSource {
    //Movies
    fun addMovie(movieEntity: MovieEntity)
    fun addMovies(movies: List<MovieEntity>)
    fun getMovieList(): DataSource.Factory<Int, MovieEntity>
    fun getMovieFavorite(): DataSource.Factory<Int, MovieEntity>
    fun getMovieDetails(id:Int): LiveData<MovieEntity>
    fun updateMovieFavorite(id: Int, isFavorite: Boolean)

    //TvShows
    fun addTvShow(tvShowEntity: TvShowEntity)
    fun addTvShows(tvShows: List<TvShowEntity>)
    fun getTvShowList(): DataSource.Factory<Int, TvShowEntity>
    fun getTvShowFavorite(): DataSource.Factory<Int, TvShowEntity>
    fun getTvShowDetails(id:Int): LiveData<TvShowEntity>
    fun updateTvShowFavorite(id: Int, isFavorite: Boolean)
}
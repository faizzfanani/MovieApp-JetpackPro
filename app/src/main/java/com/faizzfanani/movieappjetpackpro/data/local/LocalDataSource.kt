package com.faizzfanani.movieappjetpackpro.data.local

import androidx.lifecycle.LiveData
import com.faizzfanani.movieappjetpackpro.data.local.entity.MovieEntity
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity

interface LocalDataSource {
    //Movies
    fun addMovie(movieEntity: MovieEntity)
    fun addMovies(movies: List<MovieEntity>)
    fun getMovieList(): LiveData<List<MovieEntity>>
    fun getMovieDetails(id:Int): LiveData<MovieEntity>

    //TvShows
    fun addTvShow(tvShowEntity: TvShowEntity)
    fun addTvShows(tvShows: List<TvShowEntity>)
    fun getTvShowList(): LiveData<List<TvShowEntity>>
    fun getTvShowDetails(id:Int): LiveData<TvShowEntity>
}
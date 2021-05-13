package com.faizzfanani.movieappjetpackpro.data.local

import androidx.lifecycle.LiveData
import com.faizzfanani.movieappjetpackpro.data.local.dao.MovieDao
import com.faizzfanani.movieappjetpackpro.data.local.dao.TvShowDao
import com.faizzfanani.movieappjetpackpro.data.local.entity.MovieEntity
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity

class LocalDataSource private constructor(private val movieDao: MovieDao, private val tvShowDao: TvShowDao) {
    companion object{
        private var INSTANCE: LocalDataSource? = null
        fun getInstance(movieDao: MovieDao, tvShowDao: TvShowDao): LocalDataSource{
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(movieDao, tvShowDao)
            }
            return INSTANCE!!
        }
    }
    //Movies
    fun addMovie(movieEntity: MovieEntity){
        movieDao.addMovie(movieEntity)
    }
    fun addMovies(movies: List<MovieEntity>){
        movieDao.addMovies(movies)
    }
    fun getMovieList(): LiveData<List<MovieEntity>> {
        return movieDao.getMovieList()
    }
    fun getMovieDetails(id:Int):LiveData<MovieEntity>{
        return movieDao.getMovieDetails(id)
    }

    //TvShows
    fun addTvShow(tvShowEntity: TvShowEntity){
        tvShowDao.addTvShow(tvShowEntity)
    }
    fun addTvShows(tvShows: List<TvShowEntity>){
        tvShowDao.addTvShows(tvShows)
    }
    fun getTvShowList(): LiveData<List<TvShowEntity>> {
        return tvShowDao.getTvShowList()
    }
    fun getTvShowDetails(id:Int):LiveData<TvShowEntity>{
        return tvShowDao.getTvDetails(id)
    }
}
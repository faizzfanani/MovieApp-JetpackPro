package com.faizzfanani.movieappjetpackpro.data.local

import androidx.lifecycle.LiveData
import com.faizzfanani.movieappjetpackpro.data.local.dao.MovieDao
import com.faizzfanani.movieappjetpackpro.data.local.dao.TvShowDao
import com.faizzfanani.movieappjetpackpro.data.local.entity.MovieEntity
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity

open class LocalDataSourceImpl(private val movieDao: MovieDao, private val tvShowDao: TvShowDao) : LocalDataSource {
    companion object{
        private var INSTANCE: LocalDataSourceImpl? = null
        fun getInstance(movieDao: MovieDao, tvShowDao: TvShowDao): LocalDataSource{
            if (INSTANCE == null) {
                INSTANCE = LocalDataSourceImpl(movieDao, tvShowDao)
            }
            return INSTANCE!!
        }
    }
    //Movies
    override fun addMovie(movieEntity: MovieEntity){
        movieDao.addMovie(movieEntity)
    }
    override fun addMovies(movies: List<MovieEntity>){
        movieDao.addMovies(movies)
    }
    override fun getMovieList(): LiveData<List<MovieEntity>> {
        return movieDao.getMovieList()
    }
    override fun getMovieDetails(id:Int):LiveData<MovieEntity>{
        return movieDao.getMovieDetails(id)
    }

    //TvShows
    override fun addTvShow(tvShowEntity: TvShowEntity){
        tvShowDao.addTvShow(tvShowEntity)
    }
    override fun addTvShows(tvShows: List<TvShowEntity>){
        tvShowDao.addTvShows(tvShows)
    }
    override fun getTvShowList(): LiveData<List<TvShowEntity>> {
        return tvShowDao.getTvShowList()
    }
    override fun getTvShowDetails(id:Int):LiveData<TvShowEntity>{
        return tvShowDao.getTvDetails(id)
    }
}
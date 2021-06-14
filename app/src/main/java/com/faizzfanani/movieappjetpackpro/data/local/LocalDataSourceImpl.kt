package com.faizzfanani.movieappjetpackpro.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
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
        movieDao.upsert(movies)
    }
    override fun getMovieList(): DataSource.Factory<Int, MovieEntity> {
        return movieDao.getMovieList()
    }
    override fun getMovieDetails(id:Int):LiveData<MovieEntity>{
        return movieDao.getMovieDetails(id)
    }

    override fun updateMovieFavorite(id: Int, isFavorite: Boolean) {
        movieDao.updateFavorite(id, isFavorite)
    }

    //TvShows
    override fun addTvShow(tvShowEntity: TvShowEntity){
        tvShowDao.addTvShow(tvShowEntity)
    }
    override fun addTvShows(tvShows: List<TvShowEntity>){
        tvShowDao.upsert(tvShows)
    }
    override fun getTvShowList(): DataSource.Factory<Int, TvShowEntity> {
        return tvShowDao.getTvShowList()
    }
    override fun getTvShowDetails(id:Int):LiveData<TvShowEntity>{
        return tvShowDao.getTvDetails(id)
    }
    override fun updateTvShowFavorite(id: Int, isFavorite: Boolean) {
        tvShowDao.updateFavorite(id, isFavorite)
    }
}
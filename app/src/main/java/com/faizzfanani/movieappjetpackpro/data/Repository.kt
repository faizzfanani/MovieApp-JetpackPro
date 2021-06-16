package com.faizzfanani.movieappjetpackpro.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.faizzfanani.movieappjetpackpro.data.local.entity.MovieEntity
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity
import com.faizzfanani.movieappjetpackpro.vo.Resource

interface Repository {
    //Movie
    fun getMovieList(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getMovieFavorite(): LiveData<PagedList<MovieEntity>>
    fun getMovieDetails(id: Int): LiveData<Resource<MovieEntity>>
    fun updateMovieFavorite(id: Int, isFavorite: Boolean)

    //Tv Show
    fun getTvShowList(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getTvShowFavorite(): LiveData<PagedList<TvShowEntity>>
    fun getTvShowDetails(id: Int): LiveData<Resource<TvShowEntity>>
    fun updateTvShowFavorite(id: Int, isFavorite: Boolean)
}
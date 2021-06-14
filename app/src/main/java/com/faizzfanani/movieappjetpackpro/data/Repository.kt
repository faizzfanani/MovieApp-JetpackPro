package com.faizzfanani.movieappjetpackpro.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.faizzfanani.movieappjetpackpro.data.local.entity.MovieEntity
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity
import com.faizzfanani.movieappjetpackpro.vo.Resource

interface Repository {
    fun getMovieList(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getMovieDetails(id: Int): LiveData<Resource<MovieEntity>>
    fun getTvShowList(): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getTvShowDetails(id: Int): LiveData<Resource<TvShowEntity>>
}
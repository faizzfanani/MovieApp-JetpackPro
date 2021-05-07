package com.faizzfanani.movieappjetpackpro.ui.detail

import androidx.lifecycle.ViewModel
import com.faizzfanani.movieappjetpackpro.data.remote.RemoteDataSource
import com.faizzfanani.movieappjetpackpro.data.remote.response.ApiResponse
import com.faizzfanani.movieappjetpackpro.data.remote.response.Movie
import com.faizzfanani.movieappjetpackpro.data.remote.response.TvShow

class DetailViewModel(private val remoteDataSource: RemoteDataSource) : ViewModel() {
    fun getMovieDetail(id: Int) : ApiResponse<Movie> = remoteDataSource.getMovieDetail(id)
    fun getTvShowDetail(id: Int) : ApiResponse<TvShow> = remoteDataSource.getTvDetail(id)
}
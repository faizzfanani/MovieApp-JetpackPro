package com.faizzfanani.movieappjetpackpro.ui.list

import androidx.lifecycle.ViewModel
import com.faizzfanani.movieappjetpackpro.data.remote.RemoteDataSource
import com.faizzfanani.movieappjetpackpro.data.remote.response.ApiResponse
import com.faizzfanani.movieappjetpackpro.data.remote.response.Movie
import com.faizzfanani.movieappjetpackpro.data.remote.response.TvShow

class ListViewModel(private val remoteDataSource: RemoteDataSource) : ViewModel() {
    fun getMovieList() : ApiResponse<List<Movie>> = remoteDataSource.getMovieList()
    fun getTvShowList() : ApiResponse<List<TvShow>> = remoteDataSource.getTvShowList()
}
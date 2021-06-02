package com.faizzfanani.movieappjetpackpro.data.remote

import androidx.lifecycle.LiveData
import com.faizzfanani.movieappjetpackpro.data.remote.response.*

interface RemoteDataSource {
    fun getMovieList(): LiveData<ApiResponse<List<MovieResponse>>>
    fun getMovieDetail(id:Int): LiveData<ApiResponse<MovieResponse>>
    fun getTvShowList(): LiveData<ApiResponse<List<TvShowResponse>>>
    fun getTvDetail(id:Int): LiveData<ApiResponse<TvShowResponse>>
}
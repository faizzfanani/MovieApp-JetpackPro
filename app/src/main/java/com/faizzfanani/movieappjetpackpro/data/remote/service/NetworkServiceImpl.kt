package com.faizzfanani.movieappjetpackpro.data.remote.service

import com.faizzfanani.movieappjetpackpro.data.remote.response.MovieResponse
import com.faizzfanani.movieappjetpackpro.data.remote.response.MovieListResponse
import com.faizzfanani.movieappjetpackpro.data.remote.response.TvShowResponse
import com.faizzfanani.movieappjetpackpro.data.remote.response.TvShowListResponse
import com.faizzfanani.movieappjetpackpro.utils.Constant
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkServiceImpl {
    private val service : NetworkService
    private fun createAdapter() = Retrofit.Builder().baseUrl(Constant.baseUrl).addConverterFactory(
        GsonConverterFactory.create()).build()
    companion object{
        private var INSTANCE: NetworkServiceImpl? = null
        fun getInstance(): NetworkServiceImpl {
            if (INSTANCE == null) {
                INSTANCE = NetworkServiceImpl()
            }
            return INSTANCE!!
        }
    }
    init {
        val retrofit = createAdapter()
        service = retrofit.create(NetworkService::class.java)
    }
    fun getMovieList() : Call<MovieListResponse> {
        return service.getMovieList(Constant.apiKey, "en-US", 1)
    }
    fun getMovieDetail(id : Int) : Call<MovieResponse> {
        return service.getMovieDetail(id, Constant.apiKey, "en-US")
    }
    fun getTvShowList() : Call<TvShowListResponse> {
        return service.getTvShowList(Constant.apiKey, "en-US", 1)
    }
    fun getTvDetail(id : Int) : Call<TvShowResponse> {
        return service.getTvDetail(id, Constant.apiKey, "en-US")
    }
}
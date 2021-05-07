package com.faizzfanani.movieappjetpackpro.data.remote.service

import com.faizzfanani.movieappjetpackpro.data.remote.response.Movie
import com.faizzfanani.movieappjetpackpro.data.remote.response.MovieList
import com.faizzfanani.movieappjetpackpro.data.remote.response.TvShow
import com.faizzfanani.movieappjetpackpro.data.remote.response.TvShowList
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
    fun getMovieList() : Call<MovieList> {
        return service.getMovieList(Constant.baseUrl, Constant.apiKey, "en-US", 1)
    }
    fun getMovieDetail(id : Int) : Call<Movie> {
        return service.getMovieDetail(id, Constant.baseUrl, Constant.apiKey, "en-US")
    }
    fun getTvShowList() : Call<TvShowList> {
        return service.getTvShowList(Constant.baseUrl, Constant.apiKey, "en-US", 1)
    }
    fun getTvDetail(id : Int) : Call<TvShow> {
        return service.getTvDetail(id, Constant.baseUrl, Constant.apiKey, "en-US")
    }
}
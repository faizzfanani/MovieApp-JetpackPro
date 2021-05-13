package com.faizzfanani.movieappjetpackpro.data.remote.service

import com.faizzfanani.movieappjetpackpro.data.remote.response.MovieResponse
import com.faizzfanani.movieappjetpackpro.data.remote.response.MovieListResponse
import com.faizzfanani.movieappjetpackpro.data.remote.response.TvShowResponse
import com.faizzfanani.movieappjetpackpro.data.remote.response.TvShowListResponse
import com.faizzfanani.movieappjetpackpro.utils.Constant
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {
    @GET("discover/movie")
    fun getMovieList(@Query(Constant.API_KEY) apiKey : String,
                     @Query(Constant.LANGUAGE) language : String,
                     @Query(Constant.PAGE) page : Int) : Call<MovieListResponse>
    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id")id:Int,
                       @Query(Constant.API_KEY) apiKey : String,
                       @Query(Constant.LANGUAGE) language : String) : Call<MovieResponse>
    @GET("discover/tv")
    fun getTvShowList(@Query(Constant.API_KEY) apiKey : String,
                      @Query(Constant.LANGUAGE) language : String,
                      @Query(Constant.PAGE) page : Int) : Call<TvShowListResponse>
    @GET("tv/{tv_id}")
    fun getTvDetail(@Path("tv_id")id:Int,
                    @Query(Constant.API_KEY) apiKey : String,
                    @Query(Constant.LANGUAGE) language : String) : Call<TvShowResponse>
}
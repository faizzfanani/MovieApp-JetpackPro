package com.faizzfanani.movieappjetpackpro.data.remote.service

import com.faizzfanani.movieappjetpackpro.data.remote.response.Movie
import com.faizzfanani.movieappjetpackpro.data.remote.response.MovieList
import com.faizzfanani.movieappjetpackpro.data.remote.response.TvShow
import com.faizzfanani.movieappjetpackpro.data.remote.response.TvShowList
import com.faizzfanani.movieappjetpackpro.utils.Constant
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface NetworkService {
    @GET("/discover/movie")
    fun getMovieList(@Url baseUrl : String,
                     @Field(Constant.API_KEY) apiKey : String,
                     @Field(Constant.LANGUAGE) language : String,
                     @Field(Constant.PAGE) page : Int) : Call<MovieList>
    @GET("/movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id")id:Int,
                       @Url baseUrl : String,
                       @Field(Constant.API_KEY) apiKey : String,
                       @Field(Constant.LANGUAGE) language : String) : Call<Movie>
    @GET("/discover/tv")
    fun getTvShowList(@Url baseUrl : String,
                      @Field(Constant.API_KEY) apiKey : String,
                      @Field(Constant.LANGUAGE) language : String,
                      @Field(Constant.PAGE) page : Int) : Call<TvShowList>
    @GET("/tv/{tv_id}")
    fun getTvDetail(@Path("tv_id")id:Int,
                    @Url baseUrl : String,
                    @Field(Constant.API_KEY) apiKey : String,
                    @Field(Constant.LANGUAGE) language : String) : Call<TvShow>
}
package com.faizzfanani.movieappjetpackpro.data.remote

import com.faizzfanani.movieappjetpackpro.data.remote.response.*
import com.faizzfanani.movieappjetpackpro.data.remote.service.NetworkServiceImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val networkServiceImpl: NetworkServiceImpl) {
    companion object{
        private var INSTANCE: RemoteDataSource? = null
        fun getInstance(networkServiceImpl: NetworkServiceImpl): RemoteDataSource {
            if (INSTANCE == null) {
                INSTANCE = RemoteDataSource(networkServiceImpl)
            }
            return INSTANCE!!
        }
    }
    fun getMovieList() : ApiResponse<List<Movie>> {
        var result = ApiResponse<List<Movie>>()
        networkServiceImpl.getMovieList().enqueue(object : Callback<MovieList> {
            override fun onResponse(
                call: Call<MovieList>,
                response: Response<MovieList>
            ) {
                result = if (response.body()?.movieList.isNullOrEmpty()){
                    ApiResponse.Empty
                }else {
                    ApiResponse.Success(response.body()!!.movieList)
                }
            }
            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                result = ApiResponse.Error("Something went wrong with your connection")
            }

        })
        return result
    }
    fun getMovieDetail(id:Int) : ApiResponse<Movie>{
        var result = ApiResponse<Movie>()
        networkServiceImpl.getMovieDetail(id).enqueue(object : Callback<Movie>{
            override fun onResponse(
                call: Call<Movie>,
                response: Response<Movie>
            ) {
                result = if (response.body()?.id == null){
                    ApiResponse.Empty
                }else {
                    ApiResponse.Success(response.body()!!)
                }
            }
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                result = ApiResponse.Error("Something went wrong with your connection")
            }

        })
        return result
    }
    fun getTvShowList() : ApiResponse<List<TvShow>> {
        var result = ApiResponse<List<TvShow>>()
        networkServiceImpl.getTvShowList().enqueue(object : Callback<TvShowList> {
            override fun onResponse(
                call: Call<TvShowList>,
                response: Response<TvShowList>
            ) {
                result = if (response.body()?.tvShowList.isNullOrEmpty()){
                    ApiResponse.Empty
                }else {
                    ApiResponse.Success(response.body()!!.tvShowList)
                }
            }
            override fun onFailure(call: Call<TvShowList>, t: Throwable) {
                result = ApiResponse.Error("Something went wrong with your connection")
            }

        })
        return result
    }
    fun getTvDetail(id:Int) : ApiResponse<TvShow>{
        var result = ApiResponse<TvShow>()
        networkServiceImpl.getTvDetail(id).enqueue(object : Callback<TvShow>{
            override fun onResponse(
                call: Call<TvShow>,
                response: Response<TvShow>
            ) {
                result = if (response.body()?.id == null){
                    ApiResponse.Empty
                }else {
                    ApiResponse.Success(response.body()!!)
                }
            }
            override fun onFailure(call: Call<TvShow>, t: Throwable) {
                result = ApiResponse.Error("Something went wrong with your connection")
            }

        })
        return result
    }
}
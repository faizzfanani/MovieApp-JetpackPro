package com.faizzfanani.movieappjetpackpro.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.faizzfanani.movieappjetpackpro.data.remote.response.*
import com.faizzfanani.movieappjetpackpro.data.remote.service.NetworkServiceImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceImpl private constructor(private val networkServiceImpl: NetworkServiceImpl) : RemoteDataSource{
    companion object{
        private var INSTANCE: RemoteDataSourceImpl? = null
        fun getInstance(networkServiceImpl: NetworkServiceImpl): RemoteDataSource {
            if (INSTANCE == null) {
                INSTANCE = RemoteDataSourceImpl(networkServiceImpl)
            }
            return INSTANCE!!
        }
    }
    override fun getMovieList(): LiveData<ApiResponse<List<MovieResponse>>> {
        val result = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        networkServiceImpl.getMovieList().enqueue(object : Callback<MovieListResponse>{
            override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
            ) {
                if (response.body()?.movieResponseList.isNullOrEmpty()){
                    result.value = ApiResponse.Empty
                }else {
                    result.value = ApiResponse.Success(response.body()!!.movieResponseList)
                }
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                result.value = ApiResponse.Error("Something went wrong with your connection")
            }

        })
        return result
    }
    override fun getMovieDetail(id:Int): LiveData<ApiResponse<MovieResponse>>{
        val result = MutableLiveData<ApiResponse<MovieResponse>>()
        networkServiceImpl.getMovieDetail(id).enqueue(object : Callback<MovieResponse>{
            override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
            ) {
                if (response.body()?.id == null){
                    result.value = ApiResponse.Empty
                }else {
                    result.value = ApiResponse.Success(response.body()!!)
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                result.value = ApiResponse.Error("Something went wrong with your connection")
            }

        })
        return result
    }
    override fun getTvShowList(): LiveData<ApiResponse<List<TvShowResponse>>> {
        val result = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        networkServiceImpl.getTvShowList().enqueue(object : Callback<TvShowListResponse>{
            override fun onResponse(
                    call: Call<TvShowListResponse>,
                    response: Response<TvShowListResponse>
            ) {
                if (response.body()?.tvShowResponseList.isNullOrEmpty()){
                    result.value = ApiResponse.Empty
                }else {
                    result.value = ApiResponse.Success(response.body()!!.tvShowResponseList)
                }
            }

            override fun onFailure(call: Call<TvShowListResponse>, t: Throwable) {
                result.value = ApiResponse.Error("Something went wrong with your connection")
            }

        })
        return result
    }
    override fun getTvDetail(id:Int): LiveData<ApiResponse<TvShowResponse>>{
        val result = MutableLiveData<ApiResponse<TvShowResponse>>()
        networkServiceImpl.getTvDetail(id).enqueue(object : Callback<TvShowResponse>{
            override fun onResponse(
                    call: Call<TvShowResponse>,
                    response: Response<TvShowResponse>
            ) {
                if (response.body()?.id == null){
                    result.value = ApiResponse.Empty
                }else {
                    result.value = ApiResponse.Success(response.body()!!)
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                result.value = ApiResponse.Error("Something went wrong with your connection")
            }

        })
        return result
    }
}
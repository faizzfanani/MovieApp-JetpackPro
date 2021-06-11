package com.faizzfanani.movieappjetpackpro.data.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.faizzfanani.movieappjetpackpro.data.remote.response.*
import com.faizzfanani.movieappjetpackpro.data.remote.service.NetworkServiceImpl
import com.faizzfanani.movieappjetpackpro.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSourceImpl private constructor(private val networkServiceImpl: NetworkServiceImpl) : RemoteDataSource{
    private val handler = Handler(Looper.getMainLooper())
    companion object{
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000
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
        EspressoIdlingResource.increment()
        handler.postDelayed({
            networkServiceImpl.getMovieList().enqueue(object : Callback<MovieListResponse>{
                override fun onResponse(
                    call: Call<MovieListResponse>,
                    response: Response<MovieListResponse>
                ) {
                    if (response.body()?.movieResponseList.isNullOrEmpty()){
                        result.value = ApiResponse.Empty
                        EspressoIdlingResource.decrement()
                    }else {
                        result.value = ApiResponse.Success(response.body()!!.movieResponseList)
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                    result.value = ApiResponse.Error("Something went wrong with your connection")
                    EspressoIdlingResource.decrement()
                }
            })
        }, SERVICE_LATENCY_IN_MILLIS)
        return result
    }
    override fun getMovieDetail(id:Int): LiveData<ApiResponse<MovieResponse>>{
        val result = MutableLiveData<ApiResponse<MovieResponse>>()
        EspressoIdlingResource.increment()
        handler.postDelayed({
            networkServiceImpl.getMovieDetail(id).enqueue(object : Callback<MovieResponse>{
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.body()?.id == null){
                        result.value = ApiResponse.Empty
                        EspressoIdlingResource.decrement()
                    }else {
                        result.value = ApiResponse.Success(response.body()!!)
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    result.value = ApiResponse.Error("Something went wrong with your connection")
                    EspressoIdlingResource.decrement()
                }

            })
        }, SERVICE_LATENCY_IN_MILLIS)
        return result
    }
    override fun getTvShowList(): LiveData<ApiResponse<List<TvShowResponse>>> {
        val result = MutableLiveData<ApiResponse<List<TvShowResponse>>>()
        EspressoIdlingResource.increment()
        handler.postDelayed({
            networkServiceImpl.getTvShowList().enqueue(object : Callback<TvShowListResponse>{
                override fun onResponse(
                    call: Call<TvShowListResponse>,
                    response: Response<TvShowListResponse>
                ) {
                    if (response.body()?.tvShowResponseList.isNullOrEmpty()){
                        result.value = ApiResponse.Empty
                        EspressoIdlingResource.decrement()
                    }else {
                        result.value = ApiResponse.Success(response.body()!!.tvShowResponseList)
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<TvShowListResponse>, t: Throwable) {
                    result.value = ApiResponse.Error("Something went wrong with your connection")
                    EspressoIdlingResource.decrement()
                }

            })
        }, SERVICE_LATENCY_IN_MILLIS)
        return result
    }
    override fun getTvDetail(id:Int): LiveData<ApiResponse<TvShowResponse>>{
        val result = MutableLiveData<ApiResponse<TvShowResponse>>()
        EspressoIdlingResource.increment()
        handler.postDelayed({
            networkServiceImpl.getTvDetail(id).enqueue(object : Callback<TvShowResponse>{
                override fun onResponse(
                    call: Call<TvShowResponse>,
                    response: Response<TvShowResponse>
                ) {
                    if (response.body()?.id == null){
                        result.value = ApiResponse.Empty
                        EspressoIdlingResource.decrement()
                    }else {
                        result.value = ApiResponse.Success(response.body()!!)
                        EspressoIdlingResource.decrement()
                    }
                }

                override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                    result.value = ApiResponse.Error("Something went wrong with your connection")
                    EspressoIdlingResource.decrement()
                }

            })
        }, SERVICE_LATENCY_IN_MILLIS)
        return result
    }
}
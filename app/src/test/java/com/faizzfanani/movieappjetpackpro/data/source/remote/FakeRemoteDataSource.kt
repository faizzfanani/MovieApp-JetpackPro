package com.faizzfanani.movieappjetpackpro.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.faizzfanani.movieappjetpackpro.data.remote.RemoteDataSource
import com.faizzfanani.movieappjetpackpro.data.remote.response.ApiResponse
import com.faizzfanani.movieappjetpackpro.data.remote.response.MovieResponse
import com.faizzfanani.movieappjetpackpro.data.remote.response.TvShowResponse

class FakeRemoteDataSource(
    private var movieList: List<MovieResponse> = mutableListOf(),
    private var movieResponse: MovieResponse? = null,
    private var tvShowList: List<TvShowResponse> = mutableListOf(),
    private var tvShowResponse: TvShowResponse? = null
) : RemoteDataSource {
    override fun getMovieList(): LiveData<ApiResponse<List<MovieResponse>>> {
        return liveData {
            if (movieList.isNullOrEmpty()){
                emit(ApiResponse.Error("Data empty"))
            }else if (movieList.isNotEmpty()){
                emit(ApiResponse.Success(movieList))
            }
        }
    }

    override fun getMovieDetail(id: Int): LiveData<ApiResponse<MovieResponse>> {
        return liveData {
            if (movieResponse != null){
                val filterData = movieResponse.takeIf { it!!.id == id }
                if(filterData != null){
                    movieResponse?.let { emit(ApiResponse.Success(it)) }
                }else{emit(ApiResponse.Error("Data empty"))}
            }else{emit(ApiResponse.Error("Data empty"))}
        }
    }

    override fun getTvShowList(): LiveData<ApiResponse<List<TvShowResponse>>> {
        return liveData {
            if (tvShowList.isNullOrEmpty()){
                emit(ApiResponse.Error("Data empty"))
            }else if (tvShowList.isNotEmpty()){
                emit(ApiResponse.Success(tvShowList))
            }
        }
    }

    override fun getTvDetail(id: Int): LiveData<ApiResponse<TvShowResponse>> {
        return liveData {
            if (tvShowResponse != null){
                val filterData = tvShowResponse.takeIf { it!!.id == id }
                if(filterData != null){
                    tvShowResponse?.let { emit(ApiResponse.Success(it)) }
                }else{emit(ApiResponse.Error("Data empty"))}
            }else{emit(ApiResponse.Error("Data empty"))}
        }
    }
}
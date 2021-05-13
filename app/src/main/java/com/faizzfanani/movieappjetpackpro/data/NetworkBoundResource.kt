package com.faizzfanani.movieappjetpackpro.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.faizzfanani.movieappjetpackpro.data.remote.response.ApiResponse
import com.faizzfanani.movieappjetpackpro.utils.AppExecutor
import com.faizzfanani.movieappjetpackpro.vo.Resource

abstract class NetworkBoundResource<ResultType, RequestType>(appExecutor: AppExecutor) {
    private val result: MediatorLiveData<Resource<ResultType>> =
            MediatorLiveData<Resource<ResultType>>()
    private val executor: AppExecutor = appExecutor
    private fun onFetchFailed() {}
    protected abstract fun loadFromDB(): LiveData<ResultType>
    protected abstract fun shouldFetch(data: ResultType?): Boolean
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>
    protected abstract fun saveCallResult(data: RequestType)
    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse: LiveData<ApiResponse<RequestType>> = createCall()
        result.addSource(dbSource) { newData: ResultType ->
            result.setValue(
                    Resource.Loading(newData)
            )
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response) {
                is ApiResponse.Success -> executor.diskIO().execute {
                    saveCallResult(response.data)
                    executor.mainThread().execute {
                        result.addSource(
                                loadFromDB()
                        ) { newData: ResultType ->
                            result.setValue(
                                    Resource.Success(newData)
                            )
                        }
                    }
                }
                is ApiResponse.Empty -> executor.mainThread().execute {
                    result.addSource(
                            loadFromDB()
                    ) { newData: ResultType ->
                        result.setValue(
                                Resource.Success(newData)
                        )
                    }
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    result.addSource(
                            dbSource
                    ) { newData: ResultType ->
                        result.setValue(
                                Resource.Error(response.message, newData)
                        )
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<Resource<ResultType>> {
        return result
    }

    init {
        val dbSource = loadFromDB()

        result.addSource(dbSource) { data: ResultType ->
            result.setValue(
                    Resource.Loading(data)
            )
            result.removeSource(dbSource)
            if (shouldFetch(data)) {
                fetchFromNetwork(dbSource)
            } else {
                result.addSource(
                        dbSource
                ) { newData: ResultType ->
                    result.setValue(
                            Resource.Success(newData)
                    )
                }
            }
        }
    }
}
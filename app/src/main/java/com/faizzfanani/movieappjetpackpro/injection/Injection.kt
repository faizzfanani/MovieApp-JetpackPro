package com.faizzfanani.movieappjetpackpro.injection

import android.app.Application
import com.faizzfanani.movieappjetpackpro.data.remote.RemoteDataSource
import com.faizzfanani.movieappjetpackpro.data.remote.service.NetworkServiceImpl

object Injection {
    fun provideData(application: Application): RemoteDataSource {
        val serviceImpl = NetworkServiceImpl.getInstance()
        return RemoteDataSource.getInstance(serviceImpl)
    }
}
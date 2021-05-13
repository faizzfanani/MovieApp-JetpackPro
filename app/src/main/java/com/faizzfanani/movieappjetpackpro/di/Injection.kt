package com.faizzfanani.movieappjetpackpro.di

import android.app.Application
import com.faizzfanani.movieappjetpackpro.data.Repository
import com.faizzfanani.movieappjetpackpro.data.local.LocalDataSource
import com.faizzfanani.movieappjetpackpro.data.local.database.Database
import com.faizzfanani.movieappjetpackpro.data.remote.RemoteDataSource
import com.faizzfanani.movieappjetpackpro.data.remote.service.NetworkServiceImpl
import com.faizzfanani.movieappjetpackpro.utils.AppExecutor

object Injection {
    fun provideRepository(application: Application): Repository {
        val database = Database.getInstance(application)
        val localDataSource =
            LocalDataSource.getInstance(database.getMovieDao(), database.getTvShowDao())
        val serviceImpl = NetworkServiceImpl.getInstance()
        val remoteDataSource =
            RemoteDataSource.getInstance(serviceImpl)
        val appExecutor = AppExecutor()
        return Repository.getInstance(appExecutor, remoteDataSource, localDataSource)
    }
}
package com.faizzfanani.movieappjetpackpro.di

import android.app.Application
import com.faizzfanani.movieappjetpackpro.data.Repository
import com.faizzfanani.movieappjetpackpro.data.RepositoryImpl
import com.faizzfanani.movieappjetpackpro.data.local.LocalDataSourceImpl
import com.faizzfanani.movieappjetpackpro.data.local.database.Database
import com.faizzfanani.movieappjetpackpro.data.remote.RemoteDataSourceImpl
import com.faizzfanani.movieappjetpackpro.data.remote.service.NetworkServiceImpl
import com.faizzfanani.movieappjetpackpro.utils.AppExecutor

object Injection {
    fun provideRepository(application: Application): Repository {
        val database = Database.getInstance(application)
        val localDataSource =
            LocalDataSourceImpl.getInstance(database.getMovieDao(), database.getTvShowDao())
        val serviceImpl = NetworkServiceImpl.getInstance()
        val remoteDataSource =
            RemoteDataSourceImpl.getInstance(serviceImpl)
        val appExecutor = AppExecutor()
        return RepositoryImpl.getInstance(appExecutor, remoteDataSource, localDataSource)
    }
}
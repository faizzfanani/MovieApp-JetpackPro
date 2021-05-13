package com.faizzfanani.movieappjetpackpro.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.faizzfanani.movieappjetpackpro.data.Repository
import com.faizzfanani.movieappjetpackpro.di.Injection
import com.faizzfanani.movieappjetpackpro.ui.detail.DetailViewModel
import com.faizzfanani.movieappjetpackpro.ui.list.ListViewModel

class ViewModelFactory private constructor(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE =
                            ViewModelFactory(Injection.provideRepository(application))
                    }
                }
            }
            return INSTANCE!!
        }
    }
}
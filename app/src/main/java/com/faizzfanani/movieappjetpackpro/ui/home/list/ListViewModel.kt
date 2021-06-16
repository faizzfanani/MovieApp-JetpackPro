package com.faizzfanani.movieappjetpackpro.ui.home.list

import androidx.lifecycle.ViewModel
import com.faizzfanani.movieappjetpackpro.data.Repository

class ListViewModel(private val repository: Repository) : ViewModel() {
    fun getMovieList() = repository.getMovieList()
    fun getTvShowList() = repository.getTvShowList()
}
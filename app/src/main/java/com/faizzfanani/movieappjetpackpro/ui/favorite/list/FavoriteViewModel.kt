package com.faizzfanani.movieappjetpackpro.ui.favorite.list

import androidx.lifecycle.ViewModel
import com.faizzfanani.movieappjetpackpro.data.Repository

class FavoriteViewModel(private val repository: Repository) : ViewModel() {
    fun getMovieFavorite() = repository.getMovieFavorite()
    fun getTvShowFavorite() = repository.getTvShowFavorite()
}
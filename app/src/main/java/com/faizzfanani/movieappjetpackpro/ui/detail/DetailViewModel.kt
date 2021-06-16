package com.faizzfanani.movieappjetpackpro.ui.detail

import androidx.lifecycle.ViewModel
import com.faizzfanani.movieappjetpackpro.data.Repository

class DetailViewModel(private val repository: Repository) : ViewModel() {
    fun getMovieDetail(id: Int) = repository.getMovieDetails(id)
    fun updateMovieFavorite(id: Int, isFavorite: Boolean) = repository.updateMovieFavorite(id, isFavorite)
    fun getTvShowDetail(id: Int) = repository.getTvShowDetails(id)
    fun updateTvShowFavorite(id: Int, isFavorite: Boolean) = repository.updateTvShowFavorite(id, isFavorite)
}
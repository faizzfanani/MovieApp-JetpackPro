package com.faizzfanani.movieappjetpackpro.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity (
    @PrimaryKey
    val id : Int,
    val title : String,
    val overview : String,
    val posterPath : String,
    val backdropPath : String,
    val releaseDate : String,
    val voteAverage : Double,
    var isFavorite : Boolean = false)
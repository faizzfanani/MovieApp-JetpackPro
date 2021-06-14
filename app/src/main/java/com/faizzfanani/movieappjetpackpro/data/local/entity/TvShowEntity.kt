package com.faizzfanani.movieappjetpackpro.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TvShowEntity (
    @PrimaryKey
    val id : Int,
    val name : String,
    val overview : String,
    val posterPath : String,
    val backdropPath : String,
    val firstAiringDate : String,
    val voteAverage : Double,
    var isFavorite : Boolean = false)
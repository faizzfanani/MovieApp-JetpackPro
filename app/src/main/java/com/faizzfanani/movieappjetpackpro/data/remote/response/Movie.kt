package com.faizzfanani.movieappjetpackpro.data.remote.response

data class Movie (
    val id : Int,
    val title : String,
    val overview : String,
    val posterPath : String,
    val releaseDate : String,
    val voteAverage : Double,
    val adult : Boolean,
    val genres : IntArray)
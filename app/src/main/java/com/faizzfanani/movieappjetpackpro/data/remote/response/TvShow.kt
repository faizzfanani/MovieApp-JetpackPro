package com.faizzfanani.movieappjetpackpro.data.remote.response

data class TvShow (
    val id : Int,
    val name : String,
    val overview : String,
    val posterPath : String,
    val firstAiringDate : String,
    val voteAverage : Double,
    val originCountry : Array<String>,
    val genres : IntArray)
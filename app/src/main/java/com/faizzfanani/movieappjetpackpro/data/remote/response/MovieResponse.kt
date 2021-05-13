package com.faizzfanani.movieappjetpackpro.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    val id : Int,
    val title : String,
    val overview : String,
    @SerializedName("poster_path")
    val posterPath : String,
    @SerializedName("backdrop_path")
    val backdropPath : String,
    @SerializedName("release_date")
    val releaseDate : String,
    @SerializedName("vote_average")
    val voteAverage : Double)
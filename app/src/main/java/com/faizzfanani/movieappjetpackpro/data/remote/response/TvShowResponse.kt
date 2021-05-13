package com.faizzfanani.movieappjetpackpro.data.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse (
    val id : Int,
    val name : String,
    val overview : String,
    @SerializedName("poster_path")
    val posterPath : String,
    @SerializedName("backdrop_path")
    val backdropPath : String,
    @SerializedName("first_air_date")
    val firstAiringDate : String,
    @SerializedName("vote_average")
    val voteAverage : Double)
package com.faizzfanani.movieappjetpackpro.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieListResponse (@SerializedName("results") val movieResponseList : List<MovieResponse>)
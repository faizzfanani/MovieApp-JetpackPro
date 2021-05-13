package com.faizzfanani.movieappjetpackpro.data.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowListResponse (@SerializedName("results") val tvShowResponseList : List<TvShowResponse>)
package com.faizzfanani.movieappjetpackpro.ui.list.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.faizzfanani.movieappjetpackpro.R
import com.faizzfanani.movieappjetpackpro.ui.list.ListViewModel

class MovieListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private val tagMovie = "movie"
    private val tagTvShow = "tvShow"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ListViewModel::class.java]
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }
}
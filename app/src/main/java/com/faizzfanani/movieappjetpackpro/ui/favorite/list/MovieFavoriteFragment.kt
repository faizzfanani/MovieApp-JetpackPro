package com.faizzfanani.movieappjetpackpro.ui.favorite.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.faizzfanani.movieappjetpackpro.databinding.FragmentMovieFavoriteBinding
import com.faizzfanani.movieappjetpackpro.ui.home.list.movie.MovieAdapter
import com.faizzfanani.movieappjetpackpro.ui.viewmodel.ViewModelFactory

class MovieFavoriteFragment : Fragment() {
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var binding: FragmentMovieFavoriteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireActivity().application))[FavoriteViewModel::class.java]
        binding = FragmentMovieFavoriteBinding.inflate(layoutInflater)
        loadData()
        return binding.root
    }
    private fun loadData(){
        val adapter = MovieAdapter()
        binding.rvMovieFavorite.layoutManager = LinearLayoutManager(context)
        binding.rvMovieFavorite.adapter = adapter
        viewModel.getMovieFavorite().observe(viewLifecycleOwner){ list ->
            if (list.isNullOrEmpty()){
                binding.progressBar.visibility = View.GONE
                binding.labelEmpty.visibility = View.VISIBLE
            }else {
                binding.progressBar.visibility = View.GONE
                adapter.setList(list)
                }
            }
    }
}
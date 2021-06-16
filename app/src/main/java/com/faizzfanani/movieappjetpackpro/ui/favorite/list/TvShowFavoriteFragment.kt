package com.faizzfanani.movieappjetpackpro.ui.favorite.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.faizzfanani.movieappjetpackpro.databinding.FragmentTvShowFavoriteBinding
import com.faizzfanani.movieappjetpackpro.ui.home.list.tvshow.TvShowAdapter
import com.faizzfanani.movieappjetpackpro.ui.viewmodel.ViewModelFactory

class TvShowFavoriteFragment : Fragment() {
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var binding: FragmentTvShowFavoriteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(requireActivity().application))[FavoriteViewModel::class.java]
        binding = FragmentTvShowFavoriteBinding.inflate(layoutInflater)
        loadData()
        return binding.root
    }

    private fun loadData() {
        val adapter = TvShowAdapter()
        binding.rvTvShowFavorite.layoutManager = LinearLayoutManager(context)
        binding.rvTvShowFavorite.adapter = adapter
        viewModel.getTvShowFavorite().observe(viewLifecycleOwner) { list ->
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
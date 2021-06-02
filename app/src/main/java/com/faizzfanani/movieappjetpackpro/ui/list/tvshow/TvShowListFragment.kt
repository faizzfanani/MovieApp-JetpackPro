package com.faizzfanani.movieappjetpackpro.ui.list.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.faizzfanani.movieappjetpackpro.databinding.FragmentTvShowListBinding
import com.faizzfanani.movieappjetpackpro.ui.list.ListViewModel
import com.faizzfanani.movieappjetpackpro.ui.viewmodel.ViewModelFactory
import com.faizzfanani.movieappjetpackpro.vo.Resource

class TvShowListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private lateinit var binding: FragmentTvShowListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireActivity().application)
        )[ListViewModel::class.java]
        binding = FragmentTvShowListBinding.inflate(layoutInflater)
        loadData()
        return binding.root
    }

    private fun loadData() {
        val adapter = TvShowAdapter()
        binding.rvTvShow.layoutManager = LinearLayoutManager(context)
        binding.rvTvShow.adapter = adapter
        viewModel.getTvShowList().observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let {
                        adapter.setList(it)
                    }
                    binding.progressBar.visibility = View.GONE
                }
                is Resource.Loading -> {
                    resource.data?.let {
                        adapter.setList(it)
                    }
                    if (resource.data.isNullOrEmpty()) {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    resource.data?.let {
                        adapter.setList(it)
                    }
                    Toast.makeText(context, resource.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
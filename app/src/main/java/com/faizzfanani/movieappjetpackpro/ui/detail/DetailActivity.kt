package com.faizzfanani.movieappjetpackpro.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.faizzfanani.movieappjetpackpro.data.local.entity.MovieEntity
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity
import com.faizzfanani.movieappjetpackpro.databinding.ActivityDetailBinding
import com.faizzfanani.movieappjetpackpro.ui.viewmodel.ViewModelFactory
import com.faizzfanani.movieappjetpackpro.vo.Resource

class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel : DetailViewModel
    private lateinit var binding: ActivityDetailBinding
    private val tagMovie = "movie"
    private val tagTvShow = "tvShow"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(application))[DetailViewModel::class.java]
        val id = intent.getIntExtra("id", 0)
        val type = intent.getStringExtra("type")
        if (type != null) {
            loadData(id, type)
        }
    }
    private fun loadData(id: Int, type: String){
        if(type == tagMovie){
            id.let { idMovie ->
                viewModel.getMovieDetail(idMovie).observe(this){resource ->
                    when(resource){
                        is Resource.Success -> {
                            resource.data?.let {
                                setView(it)
                            }
                            binding.progressBar.visibility = View.GONE
                        }
                        is Resource.Loading -> {
                            resource.data?.let {
                                setView(it)
                            }
                        }
                        is Resource.Error -> {
                            resource.data?.let {
                                setView(it)
                            }
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, resource.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } }
        }else if(type == tagTvShow){
            id.let { idTvShow ->
                viewModel.getTvShowDetail(idTvShow).observe(this){resource ->
                    when(resource){
                        is Resource.Success -> {
                            resource.data?.let {
                                setView(it)
                            }
                            binding.progressBar.visibility = View.GONE
                        }
                        is Resource.Loading -> {
                            resource.data?.let {
                                setView(it)
                            }
                        }
                        is Resource.Error -> {
                            resource.data?.let {
                                setView(it)
                            }
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, resource.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } }
        }
    }
    private fun setView(data: Any){
        if (data is MovieEntity){
            binding.detailTitle.text = data.title
            binding.detailOverview.text = data.overview
            binding.detailRating.text = data.voteAverage.toString()
            binding.detailReleaseDate.text = data.releaseDate
            Glide.with(applicationContext).load(data.posterPath).override(500,500).into(binding.detailPoster)
            Glide.with(applicationContext).load(data.backdropPath).override(500,500).into(binding.detailBackdrop)
        }else if (data is TvShowEntity){
            binding.detailTitle.text = data.name
            binding.detailOverview.text = data.overview
            binding.detailRating.text = data.voteAverage.toString()
            binding.detailReleaseDate.text = data.firstAiringDate
            Glide.with(applicationContext).load(data.posterPath).override(500,500).into(binding.detailPoster)
            Glide.with(applicationContext).load(data.backdropPath).override(500,500).into(binding.detailBackdrop)
        }
    }
}
package com.faizzfanani.movieappjetpackpro.ui.detail

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.faizzfanani.movieappjetpackpro.R
import com.faizzfanani.movieappjetpackpro.data.local.entity.MovieEntity
import com.faizzfanani.movieappjetpackpro.data.local.entity.TvShowEntity
import com.faizzfanani.movieappjetpackpro.databinding.ActivityDetailBinding
import com.faizzfanani.movieappjetpackpro.ui.viewmodel.ViewModelFactory
import com.faizzfanani.movieappjetpackpro.utils.Constant
import com.faizzfanani.movieappjetpackpro.utils.Constant.Companion.id
import com.faizzfanani.movieappjetpackpro.utils.Constant.Companion.tagMovie
import com.faizzfanani.movieappjetpackpro.utils.Constant.Companion.tagTvShow
import com.faizzfanani.movieappjetpackpro.utils.Constant.Companion.type
import com.faizzfanani.movieappjetpackpro.vo.Resource

class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel : DetailViewModel
    private lateinit var binding: ActivityDetailBinding
    private var isFavorite = false
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.navy)))
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(application))[DetailViewModel::class.java]
        val id = intent.getIntExtra(id, 0)
        val type = intent.getStringExtra(type)
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
                                isFavorite = it.isFavorite
                                setView(it)
                                //event listener
                                binding.detailBtnFavorite.setOnClickListener { updateFavorite(id, type) }
                            }
                            binding.progressBar.visibility = View.GONE
                        }
                        is Resource.Loading -> {
                            resource.data?.let {
                                isFavorite = it.isFavorite
                                setView(it)
                            }
                        }
                        is Resource.Error -> {
                            resource.data?.let {
                                isFavorite = it.isFavorite
                                setView(it)
                                //event listener
                                binding.detailBtnFavorite.setOnClickListener { updateFavorite(id, type) }
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
                                isFavorite = it.isFavorite
                                setView(it)
                                //event listener
                                binding.detailBtnFavorite.setOnClickListener { updateFavorite(id, type) }
                            }
                            binding.progressBar.visibility = View.GONE
                        }
                        is Resource.Loading -> {
                            resource.data?.let {
                                isFavorite = it.isFavorite
                                setView(it)
                            }
                        }
                        is Resource.Error -> {
                            resource.data?.let {
                                isFavorite = it.isFavorite
                                setView(it)
                                //event listener
                                binding.detailBtnFavorite.setOnClickListener { updateFavorite(id, type) }
                            }
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this, resource.message.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } }
        }
    }
    @Suppress("DEPRECATION")
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setView(data: Any){
        if (isFavorite){
            binding.detailBtnFavorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_fill))
        }else if (!isFavorite){
            binding.detailBtnFavorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_outline))
        }
        if (data is MovieEntity){
            supportActionBar?.title = data.title
            binding.detailTitle.text = data.title
            binding.detailOverview.text = data.overview
            binding.detailRating.text = data.voteAverage.toString()
            binding.detailReleaseDate.text = data.releaseDate
            Glide.with(applicationContext).load(Constant.imageUrl+data.posterPath).override(500,500).into(binding.detailPoster)
            Glide.with(applicationContext).load(Constant.imageUrl+data.backdropPath).override(500,500).into(binding.detailBackdrop)
        }else if (data is TvShowEntity){
            supportActionBar?.title = data.name
            binding.detailTitle.text = data.name
            binding.detailOverview.text = data.overview
            binding.detailRating.text = data.voteAverage.toString()
            binding.detailReleaseDate.text = data.firstAiringDate
            Glide.with(applicationContext).load(Constant.imageUrl+data.posterPath).override(500,500).into(binding.detailPoster)
            Glide.with(applicationContext).load(Constant.imageUrl+data.backdropPath).override(500,500).into(binding.detailBackdrop)
        }
    }
    private fun updateFavorite(id: Int, type: String){
        if (type == tagMovie){
            viewModel.updateMovieFavorite(id, !isFavorite)
        }else if (type == tagTvShow){
            viewModel.updateTvShowFavorite(id, !isFavorite)
        }
    }
}
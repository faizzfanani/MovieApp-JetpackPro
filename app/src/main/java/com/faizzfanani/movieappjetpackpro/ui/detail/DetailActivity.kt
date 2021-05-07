package com.faizzfanani.movieappjetpackpro.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.faizzfanani.movieappjetpackpro.R

class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel : DetailViewModel
    private val tagMovie = "movie"
    private val tagTvShow = "tvShow"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]
        val id = intent.getIntExtra("id", 0)
        val type = intent.getStringExtra("type")
        if (type != null) {
            loadData(id, type)
        }
    }
    private fun loadData(id: Int, type: String){
        val data: Any
        if(type == tagMovie){
            data = viewModel.getMovieDetail(id)
            setView(data)

        }else if(type == tagTvShow){
            data = viewModel.getTvShowDetail(id)
            setView(data)
        }
    }
    private fun setView(data: Any){

    }
}
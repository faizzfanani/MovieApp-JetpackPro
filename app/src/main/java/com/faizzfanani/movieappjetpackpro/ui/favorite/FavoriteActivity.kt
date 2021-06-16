package com.faizzfanani.movieappjetpackpro.ui.favorite

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.faizzfanani.movieappjetpackpro.R
import com.faizzfanani.movieappjetpackpro.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)
        val favoritePagerAdapter = FavoritePagerAdapter(this, supportFragmentManager)
        activityFavoriteBinding.viewPager.adapter = favoritePagerAdapter
        activityFavoriteBinding.tabs.setupWithViewPager(activityFavoriteBinding.viewPager)
        supportActionBar?.elevation = 0f
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.navy)))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Favorites"
    }
}
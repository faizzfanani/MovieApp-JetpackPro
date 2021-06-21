package com.faizzfanani.movieappjetpackpro.ui.favorite

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.faizzfanani.movieappjetpackpro.R
import com.faizzfanani.movieappjetpackpro.databinding.ActivityFavoriteBinding
import com.faizzfanani.movieappjetpackpro.utils.Constant.Companion.titleFavorite

class FavoriteActivity : AppCompatActivity() {
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityFavoriteBinding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(activityFavoriteBinding.root)
        val favoritePagerAdapter = FavoritePagerAdapter(this, supportFragmentManager)
        activityFavoriteBinding.viewPagerFavorite.adapter = favoritePagerAdapter
        activityFavoriteBinding.tabsFavorite.setupWithViewPager(activityFavoriteBinding.viewPagerFavorite)
        supportActionBar?.elevation = 0f
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.navy)))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = titleFavorite
    }
}
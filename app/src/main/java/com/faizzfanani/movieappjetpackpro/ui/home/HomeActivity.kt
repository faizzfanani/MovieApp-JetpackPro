package com.faizzfanani.movieappjetpackpro.ui.home

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.faizzfanani.movieappjetpackpro.R
import com.faizzfanani.movieappjetpackpro.databinding.ActivityHomeBinding
import com.faizzfanani.movieappjetpackpro.ui.favorite.FavoriteActivity

class HomeActivity : AppCompatActivity() {
    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityHomeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(activityHomeBinding.root)
        val sectionsPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        activityHomeBinding.viewPager.adapter = sectionsPagerAdapter
        activityHomeBinding.tabs.setupWithViewPager(activityHomeBinding.viewPager)
        supportActionBar?.elevation = 0f
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.navy)))

        //event listener
        activityHomeBinding.btnLinkToFavorite.setOnClickListener {
            val intent = Intent(applicationContext, FavoriteActivity::class.java)
            startActivity(intent)
        }

    }
}
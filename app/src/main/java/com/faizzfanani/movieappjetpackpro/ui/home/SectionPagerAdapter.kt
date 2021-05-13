package com.faizzfanani.movieappjetpackpro.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.faizzfanani.movieappjetpackpro.R
import com.faizzfanani.movieappjetpackpro.ui.list.movie.MovieListFragment
import com.faizzfanani.movieappjetpackpro.ui.list.tvshow.TvShowListFragment

class SectionPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.title_movie_list, R.string.title_tvShow_list)
    }

    override fun getItem(position: Int): Fragment =
            when (position) {
                0 -> MovieListFragment()
                1 -> TvShowListFragment()
                else -> Fragment()
            }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = 2

}
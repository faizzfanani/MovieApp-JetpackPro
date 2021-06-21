package com.faizzfanani.movieappjetpackpro.ui.favorite

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.faizzfanani.movieappjetpackpro.R
import com.faizzfanani.movieappjetpackpro.ui.home.HomeActivity
import com.faizzfanani.movieappjetpackpro.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieFavoriteFragmentTest{
    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovieFavorite() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.detail_btnFavorite)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_btnFavorite)).perform(click())
        pressBack()
        onView(withId(R.id.btn_linkToFavorite)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_linkToFavorite)).perform(click())
        onView(withId(R.id.viewPager_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.tab_movieFavorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movieFavorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movieFavorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        pressBack()
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }
}
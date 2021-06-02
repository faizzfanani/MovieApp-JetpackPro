package com.faizzfanani.movieappjetpackpro.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.faizzfanani.movieappjetpackpro.R
import com.faizzfanani.movieappjetpackpro.utils.InstrumentalDataDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest{
    private val fakeMovieList = InstrumentalDataDummy.movieList
    private val fakeTvShowList = InstrumentalDataDummy.tvShowList

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadList() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(fakeMovieList.size))
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(fakeTvShowList.size))
        onView(withId(R.id.view_pager)).perform(swipeRight())
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.item_title)).check(matches(withText(fakeMovieList[0].title)))
        onView(withId(R.id.item_releaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.item_releaseDate)).check(matches(withText(fakeMovieList[0].releaseDate)))
        onView(withId(R.id.item_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.item_rating)).check(matches(withText(fakeMovieList[0].voteAverage.toString())))
        onView(withId(R.id.item_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.item_overview)).check(matches(withText(fakeMovieList[0].overview)))
        onView(withId(R.id.item_poster)).check(matches(isDisplayed()))
        onView(isRoot()).perform(pressBack())
    }

    @Test
    fun loadDetailTvShow() {
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.item_title)).check(matches(withText(fakeTvShowList[0].name)))
        onView(withId(R.id.item_releaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.item_releaseDate)).check(matches(withText(fakeTvShowList[0].firstAiringDate)))
        onView(withId(R.id.item_rating)).check(matches(isDisplayed()))
        onView(withId(R.id.item_rating)).check(matches(withText(fakeTvShowList[0].voteAverage.toString())))
        onView(withId(R.id.item_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.item_overview)).check(matches(withText(fakeTvShowList[0].overview)))
        onView(withId(R.id.item_poster)).check(matches(isDisplayed()))
        onView(isRoot()).perform(pressBack())
    }

}
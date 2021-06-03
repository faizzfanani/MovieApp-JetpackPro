package com.faizzfanani.movieappjetpackpro.ui.detail

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.faizzfanani.movieappjetpackpro.R
import com.faizzfanani.movieappjetpackpro.ui.home.HomeActivity
import com.google.android.material.tabs.TabLayout
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test


class DetailActivityTest{

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadMovieDetail() {
        Thread.sleep(1000)
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(0))
        Thread.sleep(1000)
        onView(withId(R.id.tab_movie)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Thread.sleep(1000)
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.detail_overview)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.detail_rating)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.detail_releaseDate)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.detail_poster)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.detail_backdrop)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        pressBack()
        Thread.sleep(1000)
    }
    @Test
    fun loadTvShowDetail() {
        Thread.sleep(1000)
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(1))
        Thread.sleep(1000)
        onView(withId(R.id.tab_tvShow)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        Thread.sleep(1000)
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.detail_overview)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.detail_rating)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.detail_releaseDate)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.detail_poster)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        onView(withId(R.id.detail_backdrop)).check(matches(isDisplayed()))
        Thread.sleep(1000)
        pressBack()
        Thread.sleep(1000)
    }

    private fun selectTabAtPosition(tabIndex: Int): ViewAction {
        return object : ViewAction {
            override fun getDescription() = "with tab at index $tabIndex"

            override fun getConstraints() = Matchers.allOf(isDisplayed(), isAssignableFrom(TabLayout::class.java))

            override fun perform(uiController: UiController, view: View) {
                val tabLayout = view as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                        ?: throw PerformException.Builder()
                                .withCause(Throwable("No tab at index $tabIndex"))
                                .build()
                tabAtIndex.select()
            }
        }
    }
}
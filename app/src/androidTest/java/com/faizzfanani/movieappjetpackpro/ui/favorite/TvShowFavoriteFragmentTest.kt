package com.faizzfanani.movieappjetpackpro.ui.favorite

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.faizzfanani.movieappjetpackpro.R
import com.faizzfanani.movieappjetpackpro.ui.home.HomeActivity
import com.faizzfanani.movieappjetpackpro.utils.EspressoIdlingResource
import com.google.android.material.tabs.TabLayout
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TvShowFavoriteFragmentTest{
    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadTvShowFavorite() {
        onView(withId(R.id.view_pager)).check(matches(isDisplayed()))
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(1))
        onView(withId(R.id.tab_tvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.detail_btnFavorite)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_btnFavorite)).perform(click())
        pressBack()
        onView(withId(R.id.btn_linkToFavorite)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_linkToFavorite)).perform(click())
        onView(withId(R.id.viewPager_favorite)).check(matches(isDisplayed()))
        onView(withId(R.id.tabs_favorite)).perform(selectTabAtPosition(1))
        onView(withId(R.id.tab_tvShowFavorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_TvShowFavorite)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_TvShowFavorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        pressBack()
    }
    @Suppress("SameParameterValue")
    private fun selectTabAtPosition(tabIndex: Int): ViewAction {
        return object : ViewAction {
            override fun getDescription() = "with tab at index $tabIndex"

            override fun getConstraints() = Matchers.allOf(
                isDisplayed(),
                ViewMatchers.isAssignableFrom(TabLayout::class.java)
            )

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

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }
}
package com.example.mobilevynils

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.mobilevynils.ui.MainActivity
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.BaseMatcher
import org.hamcrest.Description


@RunWith(AndroidJUnit4::class)
@LargeTest


class ViewListArtistsHU03 {
    @get:Rule
    var activityRule: ActivityTestRule<MainActivity?>? = ActivityTestRule(MainActivity::class.java)

    @Test fun listArtista() {
        Thread.sleep(3000)

        val iconoCollectorsArtistas =
            onView(allOf(
                withContentDescription(R.string.title_artistas),
                isDisplayed()
            ))
        iconoCollectorsArtistas.check(matches(isDisplayed()))

        iconoCollectorsArtistas.check(matches(isDisplayed()))
        iconoCollectorsArtistas.perform(click())
        Thread.sleep(4000)

        val nombreArtsitas =
            onView(allOf(withId(R.id.ArtistName),withText("Jon Secada"),
                isDisplayed()
            ))
        nombreArtsitas.check(matches(isDisplayed()))
        val imagenArtista = onView(
            first(
                withId(R.id.artistImage)
            )
        )
        imagenArtista.check(matches(isDisplayed()))


    }

    private fun <T> first(matcher: Matcher<T>): Matcher<T> {
        return object : BaseMatcher<T>() {
            var isFirst = true
            override fun matches(item: Any): Boolean {
                if (isFirst && matcher.matches(item)) {
                    isFirst = false
                    return true
                }
                return false
            }

            override fun describeTo(description: Description) {
                description.appendText("should return first matching item")
            }
        }
    }

}
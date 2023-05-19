package com.example.mobilevynils

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.mobilevynils.ui.MainActivity
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
@RunWith(AndroidJUnit4::class)
@LargeTest
class ViewAlbumesTestHU01 {
    @get:Rule
    var activityRule: ActivityTestRule<MainActivity?>? = ActivityTestRule(MainActivity::class.java)

    @Test
    fun listAlbumes() {
        Thread.sleep(3000)
        val iconoAlbumesMenu =
            Espresso.onView(
                Matchers.allOf(
                    withContentDescription(R.string.title_albumes),
                    isDisplayed()
                )
            )
        iconoAlbumesMenu.check(ViewAssertions.matches(isDisplayed()))

        iconoAlbumesMenu.check(ViewAssertions.matches(isDisplayed()))
        iconoAlbumesMenu.perform(ViewActions.click())
        Thread.sleep(4000)

        val nombreAlbum =
            Espresso.onView(
                Matchers.allOf(
                    withId(R.id.textView), withText("A Night at the Opera"),
                    isDisplayed()
                )
            )


        nombreAlbum.check(ViewAssertions.matches(isDisplayed()))

        val imagenAlbum = Espresso.onView(
            first(
                withId(R.id.albumItemImageView)
            )
        )
        imagenAlbum.check(ViewAssertions.matches(isDisplayed()))


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
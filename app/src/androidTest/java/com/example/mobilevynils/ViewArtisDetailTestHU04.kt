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
class ViewArtisDetailTestHU04 {
    @get:Rule
    var activityRule: ActivityTestRule<MainActivity?>? = ActivityTestRule(MainActivity::class.java)

    @Test
    fun DetalleArtista() {
        Thread.sleep(3000)
        val iconoCollectorsArtistas =
            Espresso.onView(
                Matchers.allOf(
                    withContentDescription(R.string.title_artistas),
                    isDisplayed()
                )
            )
        iconoCollectorsArtistas.check(ViewAssertions.matches(isDisplayed()))

        iconoCollectorsArtistas.check(ViewAssertions.matches(isDisplayed()))
        iconoCollectorsArtistas.perform(ViewActions.click())
        Thread.sleep(4000)

        val nombreArtista =
            Espresso.onView(
                Matchers.allOf(
                    withId(R.id.ArtistName), withText("Jon Secada"),
                    isDisplayed()
                )
            )
        nombreArtista.check(ViewAssertions.matches(isDisplayed()))
        nombreArtista.perform(ViewActions.click())
        Thread.sleep(3000)
        val nombreArtistaDetalle =
            Espresso.onView(
                Matchers.allOf(
                    withId(R.id.ArtistName), withText("Jon Secada"),
                    isDisplayed()
                )
            )
        nombreArtistaDetalle.check(ViewAssertions.matches(isDisplayed()))

        val imagenArtist = Espresso.onView(
            first(
                withId(R.id.artistImage)
            )
        )
        imagenArtist.check(ViewAssertions.matches(isDisplayed()))



        val BiographyDescription =
            Espresso.onView(
                Matchers.allOf(
                    withId(R.id.BiographyDescription), withText("Juan Francisco Secada Ramírez (born October 4, 1961), better known as Jon Secada, is a Cuban-born American[1][2] singer. He has won two Grammy Awards and sold 15 million records,[3] making him one of the best-selling Latin music artists. His music fuses funk, soul music, pop, and Latin percussion."),
                    isDisplayed()
                )
            )
        BiographyDescription.check(ViewAssertions.matches(isDisplayed()))

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
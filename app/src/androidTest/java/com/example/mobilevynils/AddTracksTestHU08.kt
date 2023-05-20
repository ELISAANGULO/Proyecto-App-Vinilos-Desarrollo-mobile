package com.example.mobilevynils

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.mobilevynils.ui.MainActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import java.util.*

@RunWith(AndroidJUnit4::class)
@LargeTest
class AddTracksTestHU08 {

    private val ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm"
    val timeFormat = "mm:ss"
    val sdf: SimpleDateFormat = SimpleDateFormat(timeFormat, Locale.getDefault())
    val currentTime: Date = Date()

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun asociarTrackAlbum() {
        Thread.sleep(3000)
        val iconoAlbumesMenu = Espresso.onView(
            ViewMatchers.withContentDescription(R.string.title_albumes)
        )
        iconoAlbumesMenu.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        iconoAlbumesMenu.perform(ViewActions.click())
        Thread.sleep(3000)

        val nombreAlbum =
            Espresso.onView(
                Matchers.allOf(
                    ViewMatchers.withId(R.id.textView),
                    ViewMatchers.withText("Buscando América"),
                    ViewMatchers.isDisplayed()
                )
            )
        nombreAlbum.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        nombreAlbum.perform(ViewActions.click())
        Thread.sleep(4000)

        val iconoTracks = Espresso.onView(
            ViewMatchers.withId(R.id.newTaskButton)
        )
        iconoTracks.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        iconoTracks.perform(ViewActions.click())
        Thread.sleep(3000)

        val nombreTrack = getRandomString(10)
        val duration: String = sdf.format(currentTime)

        Espresso.onView(ViewMatchers.withId(R.id.name)).perform(ViewActions.typeText(nombreTrack))
        Thread.sleep(1000)

        Espresso.onView(ViewMatchers.withId(R.id.duration)).perform(ViewActions.typeText(duration))
            //.perform(ViewActions.click(), ViewActions.replaceText("https://upload.wikimedia.org/wikipedia/en/thumb/1/1d/Adioslepido1.jpg/220px-Adioslepido1.jpg"))

        Espresso.closeSoftKeyboard()
        Thread.sleep(1000)


        Espresso.onView(ViewMatchers.withId(R.id.saveButton)).perform(ViewActions.click())
        Thread.sleep(3000)
    }

    private fun getRandomString(sizeOfRandomString: Int): String {
        val random = Random()
        val sb = StringBuilder(sizeOfRandomString)
        for (i in 0 until sizeOfRandomString) {
            sb.append(ALLOWED_CHARACTERS[random.nextInt(ALLOWED_CHARACTERS.length)])
        }
        return sb.toString()
    }
}
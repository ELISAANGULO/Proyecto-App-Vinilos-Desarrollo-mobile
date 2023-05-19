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
import java.util.Random

@RunWith(AndroidJUnit4::class)
@LargeTest
class CreateAlbumTestHU07 {

    private val ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnm"

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun crearAlbumVisible() {
        Thread.sleep(3000)
        val iconoAlbumesMenu = Espresso.onView(
            ViewMatchers.withContentDescription(R.string.title_albumes)
        )
        iconoAlbumesMenu.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        iconoAlbumesMenu.perform(ViewActions.click())
        Thread.sleep(3000)

        val iconoAlbumes = Espresso.onView(
            ViewMatchers.withId(R.id.botonCrearAlbum)
        )
        iconoAlbumes.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        iconoAlbumes.perform(ViewActions.click())
        Thread.sleep(3000)

        val nombreAlbum = getRandomString(10)
        val descripcion = getRandomString(50)

        Espresso.onView(ViewMatchers.withId(R.id.nombreAlbum)).perform(ViewActions.typeText(nombreAlbum))
        Thread.sleep(1000)

        Espresso.onView(ViewMatchers.withId(R.id.urlAlbum))
            .perform(ViewActions.click(), ViewActions.replaceText("https://upload.wikimedia.org/wikipedia/en/thumb/1/1d/Adioslepido1.jpg/220px-Adioslepido1.jpg"))

        Espresso.closeSoftKeyboard()
        Thread.sleep(1000)

        Espresso.onView(ViewMatchers.withId(R.id.fechaAlbum)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())

        Espresso.closeSoftKeyboard()
        Thread.sleep(1000)

        Espresso.onView(ViewMatchers.withId(R.id.descripcionAlbum)).perform(ViewActions.typeText(descripcion))
        Thread.sleep(3000)
        Espresso.closeSoftKeyboard()
        Thread.sleep(3000)

        Espresso.onView(ViewMatchers.withId(R.id.botonGuardarAlbum)).perform(ViewActions.click())
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
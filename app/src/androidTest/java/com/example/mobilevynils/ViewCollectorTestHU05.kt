package com.example.mobilevynils
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.mobilevynils.ui.MainActivity
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
@RunWith(AndroidJUnit4::class)
@LargeTest
class ViewCollectorTestHU05 {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity?>? = ActivityTestRule(MainActivity::class.java)

    @Test fun listCollector() {
       Thread.sleep(3000)
        val iconoCollectorsMenu =
            onView(allOf(
                withContentDescription(R.string.title_coleccionistas),
                isDisplayed()
            ))
        iconoCollectorsMenu.check(matches(isDisplayed()))

        iconoCollectorsMenu.check(matches(isDisplayed()))
        iconoCollectorsMenu.perform(click())
        Thread.sleep(4000)

        val nombreColeccionista =
            onView(allOf(withId(R.id.textViewCollector),withText("Manolo Bellon"),
                isDisplayed()
            ))
        nombreColeccionista.check(matches(isDisplayed()))

        val emailColecionista =
            onView(allOf(withId(R.id.textViewCollectorEmail),withText("manollo@caracol.com.co"),
                isDisplayed()
            ))
        emailColecionista.check(matches(isDisplayed()))


    }



}
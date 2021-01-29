package at.fh.swengb.beFast

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import at.fh.swengb.beFast.settings.SettingsActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
//checks whether the BrandsFragment, DropsFragment and MoreFragment can be clicked and are properly displayed
class BottomNavLaunchTest {
        @get:Rule
        val rule = ActivityScenarioRule(MainActivity::class.java)

        @Before
        fun setup() {
            Intents.init()
        }
        @After
        fun tearDown() {
            Intents.release()
        }

        @Test
        fun clickingBrands_shouldLaunchBrandsFragment() {
            Espresso.onView(ViewMatchers.withId(R.id.navigation_brands)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.fragment_brands)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

        @Test
        fun clickingDrops_shouldLaunchDropsFragment() {
            Espresso.onView(ViewMatchers.withId(R.id.navigation_drops)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.fragment_drops_recyclerview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }

        @Test
        fun clickingMore_shouldLaunchMoreFragment() {
            Espresso.onView(ViewMatchers.withId(R.id.navigation_more)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.fragment_more)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
}
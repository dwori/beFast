package at.fh.swengb.beFast

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
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

/**
 * checks if the Settings get displayed when clicking the the top navigation button settings in NewsFragment, BrandsFragment, DropsFragment and MoreFragment
 */

@RunWith(AndroidJUnit4::class)
class TopNavLaunchTest {
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
        fun clickingSettingsNews_shouldLaunchSettingsActivity() {
            Espresso.onView(ViewMatchers.withId(R.id.settings)).perform(ViewActions.click())
            Intents.intended(IntentMatchers.hasComponent(SettingsActivity::class.java.name))
        }

        @Test
        fun clickingSettingsBrands_shouldLaunchSettingsActivity() {
            Espresso.onView(ViewMatchers.withId(R.id.navigation_brands)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.settings)).perform(ViewActions.click())
            Intents.intended(IntentMatchers.hasComponent(SettingsActivity::class.java.name))
        }

        @Test
        fun clickingSettingsDrops_shouldLaunchDropsFragment() {
            Espresso.onView(ViewMatchers.withId(R.id.navigation_drops)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.settings)).perform(ViewActions.click())
            Intents.intended(IntentMatchers.hasComponent(SettingsActivity::class.java.name))
        }

        @Test
        fun clickingSettingsMore_shouldLaunchMoreFragment() {
            Espresso.onView(ViewMatchers.withId(R.id.navigation_more)).perform(ViewActions.click())
            Espresso.onView(ViewMatchers.withId(R.id.settings)).perform(ViewActions.click())
            Intents.intended(IntentMatchers.hasComponent(SettingsActivity::class.java.name))
        }
}
package at.fh.swengb.beFast

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
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
class LoginLogoutTest {

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
    fun logout() {
        Espresso.onView(ViewMatchers.withId(R.id.settings)).perform(ViewActions.click())
        intended(IntentMatchers.hasComponent(SettingsActivity::class.java.name))
        Espresso.onView(ViewMatchers.withId(R.id.settings_logout)).perform(ViewActions.click())
    }


    @Test
    fun workingLogin() {
        Espresso.onView(ViewMatchers.withId(R.id.settings)).perform(ViewActions.click())
        intended(IntentMatchers.hasComponent(SettingsActivity::class.java.name))
        Espresso.onView(ViewMatchers.withId(R.id.settings_login)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.login_username)).perform(ViewActions.replaceText("domi"))
        Espresso.onView(ViewMatchers.withId(R.id.login_Email)).perform(ViewActions.replaceText("domi@gmail.com"))

        Espresso.onView(ViewMatchers.withId(R.id.login_loginButton)).perform(ViewActions.click())

        Thread.sleep(2000)

        intended(IntentMatchers.hasComponent(SettingsActivity::class.java.name))
        Espresso.onView(ViewMatchers.withId(R.id.editText_username)).check(ViewAssertions.matches(ViewMatchers.withText("domi")))
        Espresso.onView(ViewMatchers.withId(R.id.editText_email)).check(ViewAssertions.matches(ViewMatchers.withText("domi@gmail.com")))
    }

}
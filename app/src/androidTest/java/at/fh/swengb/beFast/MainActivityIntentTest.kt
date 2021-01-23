package at.fh.swengb.beFast

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import at.fh.swengb.beFast.MainActivity
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.models.tweets.TweetsItem
import at.fh.swengb.beFast.news.NewsFragment
import at.fh.swengb.beFast.news.recyclerview.TweetAdapter
import at.fh.swengb.beFast.news.recyclerview.TweetViewHolder
import at.fh.swengb.beFast.settings.SettingsActivity
import kotlinx.android.synthetic.main.news_recycler_view_item.view.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityIntentTest {
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
    fun clickingSettingsButton_shouldLaunchSettingsActivity() {
        onView(withId(R.id.settings)).perform(click())
        intended(hasComponent(SettingsActivity::class.java.name))
    }
}

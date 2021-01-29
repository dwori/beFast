package at.fh.swengb.beFast

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import at.fh.swengb.beFast.drops.recyclerview.DropViewHolder
import at.fh.swengb.beFast.drops.recyclerview.description.DescriptionActivity
import at.fh.swengb.beFast.drops.recyclerview.description.DescriptionNoteActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserSavesNoteTest {
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
    fun UserSavesANote() {
        onView(withId(R.id.navigation_drops)).perform(ViewActions.click())

        Thread.sleep(2000)
        onView(withId(R.id.fragment_drops_recyclerview))
                .perform(
                        RecyclerViewActions.actionOnItem<DropViewHolder>(
                                ViewMatchers.hasDescendant(
                                        ViewMatchers.withText("Air Force 1 Rayguns")
                                ), ViewActions.click()
                        )
                )
        Intents.intended(hasComponent(DescriptionActivity::class.java.name))
        Thread.sleep(2000)
        onView(withId(R.id.description_name)).check(ViewAssertions.matches(ViewMatchers.withText("Air Force 1 Rayguns")))

        onView(withId(R.id.description_note))
                .perform(scrollTo())
                .perform(ViewActions.click())
        Intents.intended(hasComponent(DescriptionNoteActivity::class.java.name))

        onView(withId(R.id.save_note)).perform(ViewActions.click())
        Intents.intended(hasComponent(DescriptionActivity::class.java.name))
    }
}
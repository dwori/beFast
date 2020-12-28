package at.fh.swengb.beFast.ui.drops

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import at.fh.swengb.beFast.R
import kotlinx.android.synthetic.main.activity_description.*


class DescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        val description = intent.getStringExtra(DropsFragment.DESCRIPTION)

        description_view.text = description

        val actionBar: ActionBar? = actionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            android.R.id.home -> {
                this.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
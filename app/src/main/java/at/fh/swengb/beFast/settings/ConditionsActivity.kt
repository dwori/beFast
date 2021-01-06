package at.fh.swengb.beFast.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import at.fh.swengb.beFast.R

class ConditionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conditions)

        //back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    //back button
    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

}
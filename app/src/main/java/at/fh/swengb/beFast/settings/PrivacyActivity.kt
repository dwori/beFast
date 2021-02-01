package at.fh.swengb.beFast.settings

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.MenuItem
import androidx.annotation.RequiresApi
import at.fh.swengb.beFast.R
import kotlinx.android.synthetic.main.activity_privacy.*

/**
 * this class is used so that there is an activity for the privacy guidelines
 */

class PrivacyActivity : AppCompatActivity() {
    @SuppressLint("WrongConstant")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)
        privacy_text.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);

        /**this makes sure that the back button redirects to the previous fragment.*/
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

}
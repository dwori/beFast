package at.fh.swengb.beFast.ui.more

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import at.fh.swengb.beFast.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    companion object {

        val usernameKey = "USERNAME"
        val darkmodeKey = "DARKMODE"
        val loginBool = "LOGIN"
        val emailKey = "EMAIL"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        //val savedUsername = sharedPreferences.getString(usernameKey, null)
        //editText_username.setText(savedUsername)
        val savedDarkmode = sharedPreferences.getBoolean(darkmodeKey, false)
        switch_darkmode.isChecked = savedDarkmode

        //share this app
        settings_share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Get the new beFast app here: https://www.fh-joanneum.at")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        //version
        settings_version.setOnClickListener {
            Toast.makeText(this, "Version Build 3.2", Toast.LENGTH_LONG).show()
        }

        //contact us
        settings_support.setOnClickListener {
            val email = Intent(Intent.ACTION_SENDTO)
            email.data = Uri.parse("mailto:mbukvarevic@gmail.com")
            email.putExtra(Intent.EXTRA_SUBJECT, "Subject")
            email.putExtra(Intent.EXTRA_TEXT, "My Email message")
            startActivity(email)

        }


    }
    fun saveSettings(v: View) {
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        //sharedPreferences.edit().putString(usernameKey, editText_username.text.toString()).apply()
        sharedPreferences.edit().putBoolean(darkmodeKey, switch_darkmode.isChecked).apply()

        val isNightMode = sharedPreferences.getBoolean(darkmodeKey, true)
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        finish()
    }
}
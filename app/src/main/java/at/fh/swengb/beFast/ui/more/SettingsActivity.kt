package at.fh.swengb.beFast.ui.more

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import at.fh.swengb.beFast.R
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    companion object {

        val usernameKey = "USERNAME"
        val darkmodeKey = "DARKMODE"
        val loginBool = "login"
        val emailKey = "email"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        //val savedUsername = sharedPreferences.getString(usernameKey, null)
        //editText_username.setText(savedUsername)
        val savedDarkmode = sharedPreferences.getBoolean(darkmodeKey, false)
        switch_darkmode.isChecked = savedDarkmode

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
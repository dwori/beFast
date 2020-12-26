package at.fh.swengb.beFast

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {
    companion object {

        val usernamekey = "USERNAME"
        val darkmodekey = "DARKMODE"

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString(usernamekey, null)
        editText_username.setText(savedUsername)
        val savedDarkmode = sharedPreferences.getBoolean(darkmodekey, false)
        switch_darkmode.isChecked = savedDarkmode

    }
    fun saveSettings(v: View) {
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(usernamekey, editText_username.text.toString()).apply()
        sharedPreferences.edit().putBoolean(darkmodekey, switch_darkmode.isChecked).apply()

        val isNightMode = sharedPreferences.getBoolean(darkmodekey, true)
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        finish()
    }
}
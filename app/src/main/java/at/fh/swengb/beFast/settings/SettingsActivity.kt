package at.fh.swengb.beFast.settings

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.login.LoginActivity
import kotlinx.android.synthetic.main.activity_settings.*

/**
 * this class is used to display the account data of a user if he is logged in or not
 * otherwise it just acts as settings with Nightmode switch and information about the app
 */


class SettingsActivity : AppCompatActivity() {
    companion object {
        const val usernameKey = "USERNAME"
        const val nightModeKey = "NIGHTMARE"
        const val loginBoolKey = "LOGIN"
        const val emailKey = "EMAIL"
    }

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z.]+\\.+[a-z._-]+"
    private lateinit var sharedPreferences: SharedPreferences
    private var loginBoolPreferences: Boolean = false
    private var savedNightMode: Boolean = false

    override fun onResume() {
        super.onResume()
        getLoginBoolPreferences()
        loadAccountSettings()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        getLoginBoolPreferences()

        savedNightMode = sharedPreferences.getBoolean(nightModeKey, false)
        switch_nightmode.isChecked = savedNightMode

        loadAccountSettings()

        /** save Account settings */
        save_settings.setOnClickListener {
            saveSettings()
        }

        /** login button */
        settings_login.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }

        /** logout button */
        settings_logout.setOnClickListener {
            logout()
            getLoginBoolPreferences()
            loadAccountSettings()
        }

        /** share this app button */
        settings_share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, getString(R.string.get_app) + " " + "https://www.fh-joanneum.at")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        /** contact us button */
        settings_support.setOnClickListener {
            val email = Intent(Intent.ACTION_SENDTO)
            email.data = Uri.parse("mailto:mbukvarevic@gmail.com")
            email.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject))
            email.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_message))
            startActivity(email)

        }

        /** privacy button */
        settings_privacy.setOnClickListener {
            val conditionsIntent = Intent(this, PrivacyActivity::class.java)
            startActivity(conditionsIntent)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    /**this makes sure that the back button redirects to the previous fragment.*/
    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    private fun getLoginBoolPreferences() {
        sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        loginBoolPreferences = sharedPreferences.getBoolean(loginBoolKey, false)
    }
    /**
     * Function used to set the login boolean true and delete the username and email from the sharedPreferences.
     */
    private fun logout() {
        sharedPreferences.edit().putBoolean(loginBoolKey, false).apply()
        sharedPreferences.edit().remove(usernameKey).apply()
        sharedPreferences.edit().remove(emailKey).apply()
    }

    /**
     * Function to load username and email string from the sharedPreferences
     */
    private fun loadAccountSettings(){
        val savedUsername = sharedPreferences.getString(usernameKey, null)
        val savedEmail = sharedPreferences.getString(emailKey, null)

        if (loginBoolPreferences) {
            editText_username.setText(savedUsername)
            editText_email.setText(savedEmail)
            settings_login.visibility = View.GONE
            changeVisibility(View.VISIBLE)
        } else {
            settings_login.visibility = View.VISIBLE
            changeVisibility(View.GONE)
        }
    }

    /**
     * helper Function to change Views visibility
     */
    private fun changeVisibility(v: Int) {
        settings_logout.visibility = v
        TextInput_email.visibility = v
        textInput_username.visibility = v
    }

    /**
     * Function that gets executed onClick of the saveButton.
     * Saves all changes into the sharedPreferences, means: username, email and Nightmode
     * it also makes sure that the strings that get stored are valid
     * otherwise it will display errors in the textInput views
     * also it applies the changes to the Nightmode accordingly
     */
    private fun saveSettings() {
        if (loginBoolPreferences) {
            if (editText_username?.text.toString().trim().isBlank() ) {
                textInput_username.error = "Username cant be empty"
            } else {
                textInput_username.error = null
            }
            if (!editText_email?.text.toString().matches(emailPattern.toRegex()) ){
                TextInput_email.error = "no valid email"
            } else {
                TextInput_email.error = null
            }
            if (editText_email?.text.toString().matches(emailPattern.toRegex()) && !editText_username?.text.toString().trim().isBlank()){
                sharedPreferences.edit().putString(usernameKey, editText_username.text.toString()).apply()
                sharedPreferences.edit().putString(emailKey, editText_email.text.toString()).apply()
                finish()
            }
        }

        sharedPreferences.edit().putBoolean(nightModeKey, switch_nightmode.isChecked).apply()

        savedNightMode = switch_nightmode.isChecked

        if (savedNightMode) { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else { AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) }
    }
}
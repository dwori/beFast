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

class SettingsActivity : AppCompatActivity() {
    companion object {
        const val usernameKey = "USERNAME"
        const val nightModeKey = "NIGHTMARE"
        const val loginBoolKey = "LOGIN"
        const val emailKey = "EMAIL"
    }
    //Declaration of variables
    private lateinit var sharedPreferences: SharedPreferences
    private var loginBoolPreferences: Boolean = false
    private var savedNightMode: Boolean = false

    private fun getLoginBoolPreferences() {
        sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        loginBoolPreferences = sharedPreferences.getBoolean(loginBoolKey, false)
    }
    //Function used to set the login boolean true and delete the username and email from the sharedPreferences.
    private fun logout() {
        //Delete the sharedPreferences
        sharedPreferences.edit().putBoolean(loginBoolKey, false).apply()
        sharedPreferences.edit().remove(usernameKey).apply()
        sharedPreferences.edit().remove(emailKey).apply()
    }
    //Function to load username and email string from the sharedPreferences
    private fun loadAccountSettings(){
        val savedUsername = sharedPreferences.getString(usernameKey, null)
        val savedEmail = sharedPreferences.getString(emailKey, null)

        if (loginBoolPreferences) {
            textView_logged_status.text = getString(R.string.logged_in)

            editText_username.setText(savedUsername)
            editText_email.setText(savedEmail)

            settings_login.visibility = View.GONE
            changeVisibility(View.VISIBLE)

        } else {
            textView_logged_status.text = getString(R.string.logged_out)

            settings_login.visibility = View.VISIBLE
            changeVisibility(View.GONE)
        }
    }
    //Function to change Views visibility
    private fun changeVisibility(v: Int) {
        //textView_email.visibility = v
        settings_logout.visibility = v
        editText_email.visibility = v
        editText_username.visibility = v
    }
    //Function that gets executed onClick of the saveButton.
    //Saves all changes into the sharedPreferences, means: username, email and darkmode
    private fun saveSettings() {
        if (loginBoolPreferences) {
            sharedPreferences.edit().putString(usernameKey, editText_username.text.toString()).apply()
            sharedPreferences.edit().putString(emailKey, editText_email.text.toString()).apply()
        }

        sharedPreferences.edit().putBoolean(nightModeKey, switch_darkmode.isChecked).apply()

        savedNightMode = switch_darkmode.isChecked
        //Change the appearence of the app to Day- or Nightmode
        if (savedNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        getLoginBoolPreferences()

        savedNightMode = sharedPreferences.getBoolean(nightModeKey, false)
        switch_darkmode.isChecked = savedNightMode

        loadAccountSettings()

        //save Account settings
        save_settings.setOnClickListener {
            saveSettings()
            finish()
        }

        //login button
        settings_login.setOnClickListener {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        }

        //logout button
        settings_logout.setOnClickListener {
            logout()
            getLoginBoolPreferences()
            loadAccountSettings()
        }

        //share this app button
        settings_share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, getString(R.string.get_app) + " " + "https://www.fh-joanneum.at")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        //contact us button
        settings_support.setOnClickListener {
            val email = Intent(Intent.ACTION_SENDTO)
            email.data = Uri.parse("mailto:mbukvarevic@gmail.com")
            email.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject))
            email.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_message))
            startActivity(email)

        }

        //terms and conditions button
        settings_conditions.setOnClickListener {
            val conditionsIntent = Intent(this, ConditionsActivity::class.java)
            startActivity(conditionsIntent)
        }

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
    override fun onResume() {
        super.onResume()
        getLoginBoolPreferences()
        loadAccountSettings()
    }
}
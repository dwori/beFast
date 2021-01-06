package at.fh.swengb.beFast.ui.more

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import at.fh.swengb.beFast.MainActivity
import at.fh.swengb.beFast.R
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.fragment_more.*


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
        if (sharedPreferences.getBoolean(SettingsActivity.loginBool, false)) {
            settings_login.visibility = View.GONE
            settings_logout.visibility = View.VISIBLE
            textView_logged_status.text = getString(R.string.logged_in)
            textView_email.visibility = View.VISIBLE
            val savedUsername = sharedPreferences.getString(usernameKey, null)
            editText_username.setText(savedUsername)
            editText_email.setText(sharedPreferences.getString(emailKey, null))



        } else {
            textView_logged_status.text = getString(R.string.logged_out)
            editText_username.visibility = View.GONE
            editText_email.visibility = View.GONE
            textView_email.visibility = View.GONE
            settings_logout.visibility = View.GONE

        }
        settings_login.setOnClickListener {

            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)

        }

        settings_logout.setOnClickListener {
            sharedPreferences.edit().putBoolean(SettingsActivity.loginBool, false).apply()
            /*editUsername.setVisibility(View.VISIBLE)
            editEmail.setVisibility(View.VISIBLE)
            editUsername.setText("")
            editEmail.setText("")*/
            textView_logged_status.text = getString(R.string.logged_out)
            textView_email.visibility = View.GONE
            settings_login.visibility = View.VISIBLE
            editText_username.visibility = View.INVISIBLE
            editText_email.visibility = View.INVISIBLE
            settings_logout.visibility = View.INVISIBLE



            //Delete the sharedPreferences
            //Log.i("INFO", "Prefs deleted")
            sharedPreferences.edit().remove(usernameKey).commit()
            sharedPreferences.edit().remove(emailKey).commit()
        }



        val savedUsername = sharedPreferences.getString(usernameKey, null)
        editText_username.setText(savedUsername)
        val savedDarkmode = sharedPreferences.getBoolean(darkmodeKey, false)
        switch_darkmode.isChecked = savedDarkmode

        //share this app
        settings_share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, getString(R.string.get_app) + " " + "https://www.fh-joanneum.at")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }



        //contact us
        settings_support.setOnClickListener {
            val email = Intent(Intent.ACTION_SENDTO)
            email.data = Uri.parse("mailto:mbukvarevic@gmail.com")
            email.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject))
            email.putExtra(Intent.EXTRA_TEXT, getString(R.string.email_message))
            startActivity(email)

        }

        //terms and conditions
        settings_conditions.setOnClickListener {
            val conditionsIntent = Intent(this, ConditionsActivity::class.java)
            startActivity(conditionsIntent)
        }

        //back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)




    }
    fun saveSettings(v: View) {
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(usernameKey, editText_username.text.toString()).apply()
        sharedPreferences.edit().putString(emailKey, editText_email.text.toString()).apply()
        sharedPreferences.edit().putBoolean(darkmodeKey, switch_darkmode.isChecked).apply()

        val isNightMode = sharedPreferences.getBoolean(darkmodeKey, true)
        if (isNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }


        finish()
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
        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean(SettingsActivity.loginBool, false)) {
            settings_login.visibility = View.GONE
            textView_email.visibility = View.VISIBLE
            settings_logout.visibility = View.VISIBLE
            editText_email.visibility = View.VISIBLE
            editText_username.visibility = View.VISIBLE
            textView_logged_status.text = getString(R.string.logged_in)
            val savedUsername = sharedPreferences.getString(usernameKey, null)
            editText_username.setText(savedUsername)
            editText_email.setText(sharedPreferences.getString(emailKey, null))



        } else {
            textView_logged_status.text = getString(R.string.logged_out)

            textView_email.visibility = View.GONE
            editText_email.visibility = View.GONE
            editText_username.visibility = View.GONE
            settings_logout.visibility = View.GONE

        }

    }







}
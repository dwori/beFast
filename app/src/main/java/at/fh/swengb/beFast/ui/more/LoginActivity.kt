package at.fh.swengb.beFast.ui.more

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import at.fh.swengb.beFast.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_more.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        login_loginButton.setOnClickListener {
            if (login_username?.text.toString().trim().isNullOrBlank() || login_password?.text.toString()
                    .trim().isNullOrBlank()
            ) {
                Toast.makeText(this, "Please enter a valid username & email.", Toast.LENGTH_LONG)
                    .show()

                //on login we change the visibility of the editTexts and the login/logout buttons.
            } else {
                sharedPreferences.edit()
                    .putString(SettingsActivity.usernameKey, login_username.text.toString()).apply()
                sharedPreferences.edit()
                    .putString(SettingsActivity.emailKey, login_password.text.toString()).apply()
                sharedPreferences.edit().putBoolean(SettingsActivity.loginBool, true).apply()
                finish()
            }
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }
    // back button
    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }


}

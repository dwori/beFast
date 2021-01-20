package at.fh.swengb.beFast.login


import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import at.fh.swengb.beFast.settings.SettingsActivity
import at.fh.swengb.beFast.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        //OnClick of loginbutton the provided username and email is checked if they are empty a toast with an error message is shown
        login_loginButton.setOnClickListener {
            if (login_username?.text.toString().trim().isBlank() || login_password?.text.toString().trim().isBlank()) {
                Toast
                        .makeText(this, getString(R.string.valid_email), Toast.LENGTH_LONG)
                        .show()
            } else {
                sharedPreferences
                        .edit()
                        .putString(SettingsActivity.usernameKey, login_username.text.toString())
                        .apply()
                sharedPreferences
                        .edit()
                        .putString(SettingsActivity.emailKey, login_password.text.toString())
                        .apply()
                sharedPreferences
                        .edit()
                        .putBoolean(SettingsActivity.loginBoolKey, true)
                        .apply()
                finish()
            }
        }

        // back button
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

package at.fh.swengb.beFast.login


import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import at.fh.swengb.beFast.settings.SettingsActivity
import at.fh.swengb.beFast.R

/**
 * this class is used to represent the logic of a user entering their username and email.
 * it also handles exceptions like empty username and invalid email.
 */
class LoginActivity : AppCompatActivity() {

    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z.]+\\.+[a-z._-]+"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)

        login_loginButton.setOnClickListener {
            /** checking the input of email and username */
            if (login_username?.text.toString().trim().isBlank() || !login_Email?.text.toString().matches(emailPattern.toRegex())) {
                Toast.makeText(this, getString(R.string.valid_email), Toast.LENGTH_LONG).show()
            } else {
                /** when logged in, save the keys & values in the shared preferences */
                sharedPreferences.edit().putString(SettingsActivity.usernameKey, login_username.text.toString()).apply()
                sharedPreferences.edit().putString(SettingsActivity.emailKey, login_Email.text.toString()).apply()
                sharedPreferences.edit().putBoolean(SettingsActivity.loginBoolKey, true).apply()
                finish()
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    /** this makes sure that the back button redirects to the previous fragment.*/
    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}

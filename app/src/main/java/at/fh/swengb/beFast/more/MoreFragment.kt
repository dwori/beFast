package at.fh.swengb.beFast.more

import androidx.fragment.app.Fragment
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import at.fh.swengb.beFast.Menu.consume
import kotlinx.android.synthetic.main.fragment_more.*
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.login.LoginActivity
import at.fh.swengb.beFast.settings.SettingsActivity
import at.fh.swengb.beFast.settings.SettingsActivity.Companion.loginBoolKey
import at.fh.swengb.beFast.settings.SettingsActivity.Companion.emailKey
import at.fh.swengb.beFast.settings.SettingsActivity.Companion.usernameKey


class MoreFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private var loginBoolPreferences: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_more, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadMoreUserDetails()

        logoutButton.setOnClickListener {
            logout()
            loadMoreUserDetails()
        }

        loginButton.setOnClickListener {
            val loginIntent = Intent(context, LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }

    override fun onResume() {
        super.onResume()
        loadMoreUserDetails()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_nav_menu, menu);
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.settings -> consume { settings() }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /**
     * This method loads all the needed values stored in the shared preferences
     * If the login boolean in the sharedPreferences is true, the loginButton is invisible and the logoutButton is visible.
     * The header text is set to logged_in which is stored in the strings.xml
     * Else the exact opposite will happen and the looged_out text is shown.
     */
    private fun loadMoreUserDetails(){
        sharedPreferences = requireContext().getSharedPreferences(requireContext().packageName, Context.MODE_PRIVATE)
        loginBoolPreferences = sharedPreferences.getBoolean(loginBoolKey, false)

        login_name.text = sharedPreferences.getString(usernameKey, "")
        login_email.text = sharedPreferences.getString(emailKey, "")

        if (loginBoolPreferences) {
            more_header_text.text =  getString(R.string.logged_in)
            loginButton.visibility = View.GONE
            logoutButton.visibility = View.VISIBLE

        } else {
            more_header_text.text = getString(R.string.logged_out)
            loginButton.visibility = View.VISIBLE
            logoutButton.visibility = View.GONE
        }
    }

    /** Method used to set the login boolean true and delete the username and email from the sharedPreferences. */
    private fun logout() {
        sharedPreferences.edit().putBoolean(loginBoolKey, false).apply()
        sharedPreferences.edit().remove(usernameKey).apply()
        sharedPreferences.edit().remove(emailKey).apply()
    }

    private fun settings(){
        val intent = Intent(activity, SettingsActivity::class.java)
        startActivity(intent)
    }
}
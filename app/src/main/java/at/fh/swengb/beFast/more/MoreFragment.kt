package at.fh.swengb.beFast.more

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_more.*
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.login.LoginActivity
import at.fh.swengb.beFast.settings.SettingsActivity.Companion.loginBoolKey
import at.fh.swengb.beFast.settings.SettingsActivity.Companion.emailKey
import at.fh.swengb.beFast.settings.SettingsActivity.Companion.usernameKey


class MoreFragment : Fragment() {

    private lateinit var moreViewModel: MoreViewModel

    private lateinit var sharedPreferences: SharedPreferences
    private var loginBoolPreferences: Boolean = false

    private fun loadMoreUserDetails(){
        sharedPreferences = requireContext().getSharedPreferences(requireContext().packageName, Context.MODE_PRIVATE)
        loginBoolPreferences = sharedPreferences.getBoolean(loginBoolKey, false)

        login_name.text = sharedPreferences.getString(usernameKey, "null")
        login_email.text = sharedPreferences.getString(emailKey, "null")

        if (loginBoolPreferences) {
            logged_in_as.text =  getString(R.string.logged_in) // todo: better xml name

            loginButton.visibility = View.GONE
            logoutButton.visibility = View.VISIBLE

        } else {
            logged_in_as.text = getString(R.string.logged_out)

            loginButton.visibility = View.VISIBLE
            logoutButton.visibility = View.GONE
        }
    }
    private fun logout() {
        //Delete the sharedPreferences
        sharedPreferences.edit().putBoolean(loginBoolKey, false).apply()
        sharedPreferences.edit().remove(usernameKey).apply()
        sharedPreferences.edit().remove(emailKey).apply()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        moreViewModel = ViewModelProvider(this).get(MoreViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_more, container, false)
        val textView: TextView = root.findViewById(R.id.text_more)
        moreViewModel.text.observe(viewLifecycleOwner, Observer { textView.text = it })
        return root
    }

    // on Create Fragment
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
}
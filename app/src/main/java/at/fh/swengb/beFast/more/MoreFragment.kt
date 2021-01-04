package at.fh.swengb.beFast.more

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_more.*
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.login.LoginActivity
import at.fh.swengb.beFast.settings.SettingsActivity
import at.fh.swengb.beFast.settings.SettingsActivity.Companion.emailKey
import at.fh.swengb.beFast.settings.SettingsActivity.Companion.usernameKey


class MoreFragment : Fragment() {

    private lateinit var moreViewModel: MoreViewModel

    private fun loadUserDetails(){
        val sharedPreferences = requireContext().getSharedPreferences(requireContext().packageName, Context.MODE_PRIVATE)
        if (sharedPreferences.getBoolean(SettingsActivity.loginBool, false)) {
            logged_in_as.text =  getString(R.string.logged_in) // todo: better xml name

            loginButton.visibility = View.GONE
            logoutButton.visibility = View.VISIBLE

            login_name.visibility = View.VISIBLE
            login_email.visibility = View.VISIBLE

            login_name.text = sharedPreferences.getString(usernameKey, null)
            login_email.text = sharedPreferences.getString(emailKey, null)
        } else {
            logged_in_as.text = getString(R.string.logged_out)

            loginButton.visibility = View.VISIBLE
            logoutButton.visibility = View.GONE

            login_name.visibility = View.GONE
            login_email.visibility = View.GONE
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        moreViewModel = ViewModelProvider(this).get(MoreViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_more, container, false)
        val textView: TextView = root.findViewById(R.id.text_more)
        moreViewModel.text.observe(viewLifecycleOwner, Observer { textView.text = it })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadUserDetails()

        val sharedPreferences = requireContext().getSharedPreferences(requireContext().packageName, Context.MODE_PRIVATE)

        logoutButton.setOnClickListener {
            //Delete the sharedPreferences
            sharedPreferences.edit().putBoolean(SettingsActivity.loginBool, false).apply()
            sharedPreferences.edit().remove(usernameKey).apply()
            sharedPreferences.edit().remove(emailKey).apply()

            logged_in_as.text = getString(R.string.logged_out) // todo: better xml name

            logoutButton.visibility = View.GONE
            loginButton.visibility = View.VISIBLE

            login_name.visibility = View.GONE
            login_email.visibility = View.GONE
        }

        loginButton.setOnClickListener {
            val loginIntent = Intent(context, LoginActivity::class.java)
            startActivity(loginIntent)
        }
    }
    override fun onResume() {
        super.onResume()
        loadUserDetails()
    }
}
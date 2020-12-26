package at.fh.swengb.beFast.ui.more

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import at.fh.swengb.beFast.R
import kotlinx.android.synthetic.main.fragment_more.*


class MoreFragment : Fragment() {

    private lateinit var moreViewModel: MoreViewModel



    public var isLoggedIn = false

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?

    ): View? {
        moreViewModel =
                ViewModelProvider(this).get(MoreViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_more, container, false)
        val textView: TextView = root.findViewById(R.id.text_more)
        moreViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.onActivityCreated(savedInstanceState)
        val sharedPreferences = requireContext().getSharedPreferences(requireContext().packageName, Context.MODE_PRIVATE)

        if (sharedPreferences.getBoolean(SettingsActivity.loginBool, false)) {
            login_name.text = sharedPreferences.getString(SettingsActivity.usernameKey, null)
            login_email.text = sharedPreferences.getString(SettingsActivity.emailKey, null)
            logged_in_as.text = "YOU ARE LOGGED IN"
            loginButton.setVisibility(View.GONE)
            logoutButton.setVisibility(View.VISIBLE)

        } else {
            editUsername.setText(sharedPreferences.getString(SettingsActivity.usernameKey, null))
            editEmail.setText(sharedPreferences.getString(SettingsActivity.emailKey, null))
            logged_in_as.text = "YOU ARE LOGGED OUT"
            logoutButton.setVisibility(View.GONE)
            loginButton.setVisibility(View.VISIBLE)

        }

        loginButton.setOnClickListener {
            sharedPreferences.edit().putString(SettingsActivity.usernameKey, editUsername.text.toString()).apply()
            sharedPreferences.edit().putString(SettingsActivity.emailKey, editEmail.text.toString()).apply()
            sharedPreferences.edit().putBoolean(SettingsActivity.loginBool, true).apply()
            editUsername.setVisibility(View.GONE)
            editEmail.setVisibility(View.GONE)
            logged_in_as.text = "YOU ARE LOGGED IN"
            loginButton.setVisibility(View.GONE)
            logoutButton.setVisibility(View.VISIBLE)

            login_name.text = sharedPreferences.getString(SettingsActivity.usernameKey, null)
            login_email.text = sharedPreferences.getString(SettingsActivity.emailKey, null)

        }
        logoutButton.setOnClickListener {
            sharedPreferences.edit().putBoolean(SettingsActivity.loginBool, false).apply()
            editUsername.setVisibility(View.VISIBLE)
            editEmail.setVisibility(View.VISIBLE)
            logged_in_as.text = "YOU ARE LOGGED OUT"
            logoutButton.setVisibility(View.GONE)
            loginButton.setVisibility(View.VISIBLE)

            login_name.text = ""
            login_email.text = ""
        }
    }




        /*
        editUsername.setText(savedUsername)

        editEmail.setText(savedEmail)
        */



    }



//TODO: update fragment when sign-in and sign-out process is complete
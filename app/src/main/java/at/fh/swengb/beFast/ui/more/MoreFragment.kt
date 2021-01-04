package at.fh.swengb.beFast.ui.more

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.ui.more.SettingsActivity.Companion.emailKey
import at.fh.swengb.beFast.ui.more.SettingsActivity.Companion.usernameKey
import kotlinx.android.synthetic.main.fragment_more.*


class MoreFragment : Fragment() {

    private lateinit var moreViewModel: MoreViewModel


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
        Log.i("AC","on AC")
        super.onActivityCreated(savedInstanceState)
        super.onActivityCreated(savedInstanceState)
        val sharedPreferences = requireContext().getSharedPreferences(requireContext().packageName, Context.MODE_PRIVATE)

        //If the user is logged in, it should  show this as a message and hide the login button, but show the logout button
        //Also the username and the user´s email get displayed
        if (sharedPreferences.getBoolean(SettingsActivity.loginBool, false)) {
            login_name.text = sharedPreferences.getString(usernameKey, null)
            login_email.text = sharedPreferences.getString(emailKey, null)
            logged_in_as.text =  getString(R.string.logged_in)
            loginButton.setVisibility(View.GONE)
            logoutButton.setVisibility(View.VISIBLE)
        } else {
            //Else the logoutButton gets hidden and the login one shown. And a matching message is shown on the screen
            /*editUsername.setText(sharedPreferences.getString(usernameKey, null))
            editEmail.setText(sharedPreferences.getString(emailKey, null))*/


            logged_in_as.text = getString(R.string.logged_out)
            logoutButton.setVisibility(View.GONE)
            loginButton.setVisibility(View.VISIBLE)
        }

        loginButton.setOnClickListener {
            val loginIntent = Intent(context, LoginActivity::class.java)
            startActivity(loginIntent)
        }

        logoutButton.setOnClickListener {
            sharedPreferences.edit().putBoolean(SettingsActivity.loginBool, false).apply()
            /*editUsername.setVisibility(View.VISIBLE)
            editEmail.setVisibility(View.VISIBLE)
            editUsername.setText("")
            editEmail.setText("")*/
            logged_in_as.text = getString(R.string.logged_out)
            logoutButton.setVisibility(View.GONE)
            loginButton.setVisibility(View.VISIBLE)

            login_name.text = ""
            login_email.text = ""

            //Delete the sharedPreferences
            //Log.i("INFO", "Prefs deleted")
            sharedPreferences.edit().remove(usernameKey).commit()
            sharedPreferences.edit().remove(emailKey).commit()
        }
    }
    override fun onResume() {
        super.onResume()
        //Log.i("INFO", "onResume")
        val sharedPreferences = requireContext().getSharedPreferences(requireContext().packageName, Context.MODE_PRIVATE)
        //If the fragment is switched back to the More fragment the editTexts will still be hidden in logged in state.

        if (sharedPreferences.getBoolean(SettingsActivity.loginBool, false)) {
            login_name.text = sharedPreferences.getString(usernameKey, null)
            login_email.text = sharedPreferences.getString(emailKey, null)
            logged_in_as.text =  getString(R.string.logged_in)
            loginButton.setVisibility(View.GONE)
            logoutButton.setVisibility(View.VISIBLE)
        } else {
            //Else the logoutButton gets hidden and the login one shown. And a matching message is shown on the screen

            logged_in_as.text = getString(R.string.logged_out)
            logoutButton.setVisibility(View.GONE)
            loginButton.setVisibility(View.VISIBLE)
        }
    }







    }



//TODO: update fragment when sign-in and sign-out process is complete
//TODO: Replace hardcoded strings

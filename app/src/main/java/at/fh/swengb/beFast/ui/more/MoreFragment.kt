package at.fh.swengb.beFast.ui.more

import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
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

        //If the user is logged in, it should  show this as a message and hide the login button, but show the logout button
        //Also the username and the user´s email get displayed
        if (sharedPreferences.getBoolean(SettingsActivity.loginBool, false)) {
            login_name.text = sharedPreferences.getString(SettingsActivity.usernameKey, null)
            login_email.text = sharedPreferences.getString(SettingsActivity.emailKey, null)
            logged_in_as.text = "YOU ARE LOGGED IN"
            loginButton.setVisibility(View.GONE)
            logoutButton.setVisibility(View.VISIBLE)

        } else {
            //Else the logoutButton gets hidden and the login one shown. And a matching message is shown on the screen
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
    override fun  onResume(){
        super.onResume()
        //Log.i("INFO", "onResume")
        val sharedPreferences = requireContext().getSharedPreferences(requireContext().packageName, Context.MODE_PRIVATE)
        //If the fragment is switched back to the More fragment the editTexts will still be hidden in logged in mode.
        if (sharedPreferences.getBoolean(SettingsActivity.loginBool, false)) {
            editUsername.setVisibility(View.GONE)
            editEmail.setVisibility(View.GONE)
            //Log.i("INFO","editTexts hidden")
        }else{
            editUsername.setVisibility(View.VISIBLE)
            editEmail.setVisibility(View.VISIBLE)
            //Log.i("INFO","editTexts shown")
        }
    }







    }



//TODO: update fragment when sign-in and sign-out process is complete

package at.fh.swengb.beFast.ui.more

import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
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
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.dynamite.DynamiteModule.load
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.fragment_more.*
import java.lang.System.load
import java.util.ServiceLoader.load


class MoreFragment : Fragment() {

    private lateinit var moreViewModel: MoreViewModel

    companion object {
        val usernameKey = "USERNAME"
        val passwordKey = "PASSWORD"
        val emailKey = "EMAIL"
    }

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

        val sharedPreferences = activity?.getSharedPreferences("at.fh.swengb.beFast", Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences?.getString("USERNAME",null)
        val savedPassword = sharedPreferences?.getString("PASSWORD",null)
        val savedEmail = sharedPreferences?.getString("EMAIL",null)

        /*
        editUsername.setText(savedUsername)
        editPassword.setText(savedPassword)
        editEmail.setText(savedEmail)
        */



    }

}

//TODO: update fragment when sign-in and sign-out process is complete
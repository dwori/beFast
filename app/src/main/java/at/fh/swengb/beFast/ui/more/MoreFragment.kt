package at.fh.swengb.beFast.ui.more

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.ui.news.NewsFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
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
    // create google sign in and google sign in client
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        val RC_SIGN_IN = 0
        super.onActivityCreated(savedInstanceState)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val mGoogleSignInClient = activity?.let { GoogleSignIn.getClient(it, gso) }

        fun signIn() {
            val signInIntent: Intent
            if (mGoogleSignInClient != null) {
                signInIntent = mGoogleSignInClient.getSignInIntent()
                startActivityForResult(signInIntent, RC_SIGN_IN)
            }

        }

        sign_in_button.setOnClickListener {
            signIn()
        }



    }
    // check if account signed in or not
    fun updateUI(account: GoogleSignInAccount?) {
        if (account != null) {
            // TODO: hide log-in interface and show username
            Toast.makeText(activity, "Already signed in", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(activity, "Sign in", Toast.LENGTH_LONG).show()
        }
    }
    // call the updateUI function when starting
    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(activity)
        updateUI(account)
    }

    // sign-in method

}
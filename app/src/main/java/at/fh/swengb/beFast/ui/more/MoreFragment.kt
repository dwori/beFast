package at.fh.swengb.beFast.ui.more

import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
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
        val RC_SIGN_IN = 0
    }

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
        super.onActivityCreated(savedInstanceState)

        //create sign in
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val mGoogleSignInClient = activity?.let { GoogleSignIn.getClient(it, gso) }
        //sign in method
        fun signIn() {
            val signInIntent: Intent
            if (mGoogleSignInClient != null) {
                signInIntent = mGoogleSignInClient.getSignInIntent()
                startActivityForResult(signInIntent, RC_SIGN_IN)
            }

        }
        //signOut method
        fun signOut() {
            if (mGoogleSignInClient != null) {
                activity?.let {
                    mGoogleSignInClient.signOut()
                            .addOnCompleteListener(it, OnCompleteListener<Void?> {
                                Log.i("MoreFragment", "SignedOut")
                                onStart()
                                updateUI(account = null)

                            })
                }

                //updateUI(account = null)
            }
        }



        sign_in_button.setOnClickListener {
            signIn()
        }

        sign_out_button.setOnClickListener {
            signOut()
        }




    }
    // check if account signed in or not
    fun updateUI(account: GoogleSignInAccount?) {
        if (account != null) {

            sign_in_button.setVisibility(View.GONE)
            login_name.text = account.displayName
            login_email.text = account.email


        } else {
            sign_out_button.setVisibility(View.GONE)
            login_name.setVisibility(View.GONE)
            login_email.setVisibility(View.GONE)
            logged_in_as.setVisibility(View.GONE)

        }
    }
    // call the updateUI function when starting
    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(activity)
        updateUI(account)
    }

    // handle the sign in result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === RC_SIGN_IN && resultCode == RESULT_OK) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }
    }

}

//TODO: update fragment when sign-in and sign-out process is complete
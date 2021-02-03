package at.fh.swengb.beFast.brands

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import at.fh.swengb.beFast.Menu.consume
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.settings.SettingsActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_brands.*

/**
 * The BrandsFragment holds different brands in a table and handles how to favor single brands by assigning a switch to every one
 * of them. By turning on a switch, the brand and its drops get shown in the DropsFragment.
 */

class BrandsFragment : Fragment() {
    companion object {
        val nikeKey = "Nike"
        val adidasKey = "Adidas"
        val fearofgofKey = "Fear Of God"
        val newbalanceKey = "New Balance"
        val pumaKey = "Puma"
        val supremeKey = "Supreme"
    }

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_brands, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /**
         * We get the shared Preferences in order get and put boolean keys.
         **/
        sharedPreferences = requireContext().getSharedPreferences(requireContext().packageName, Context.MODE_PRIVATE)

        /**
         * First we check if the user is logged in (loginBoolKey)
         * If this is the case, we put every Brands' switch to default = true
         */
        if (sharedPreferences.getBoolean(SettingsActivity.loginBoolKey, false)) {
            switch1.isChecked = sharedPreferences.getBoolean(nikeKey, true)

            switch2.isChecked = sharedPreferences.getBoolean(adidasKey, true)

            switch3.isChecked = sharedPreferences.getBoolean(fearofgofKey, true)

            switch4.isChecked = sharedPreferences.getBoolean(newbalanceKey, true)

            switch5.isChecked = sharedPreferences.getBoolean(pumaKey, true)

            switch6.isChecked = sharedPreferences.getBoolean(supremeKey, true)

            /**
             * The button save_brands save the current state of one brand by checking
             * whether the switch is on or off
             */

            save_brands.setOnClickListener {
                sharedPreferences.edit().putBoolean(nikeKey, switch1.isChecked).apply()
                sharedPreferences.edit().putBoolean(adidasKey, switch2.isChecked).apply()
                sharedPreferences.edit().putBoolean(fearofgofKey, switch3.isChecked).apply()
                sharedPreferences.edit().putBoolean(newbalanceKey, switch4.isChecked).apply()
                sharedPreferences.edit().putBoolean(pumaKey, switch5.isChecked).apply()
                sharedPreferences.edit().putBoolean(supremeKey, switch6.isChecked).apply()
                Toast.makeText(activity, getString(R.string.brands_saved), Toast.LENGTH_LONG).show()
            }
            brands_info.visibility = View.GONE

        } else {
            switch1.visibility = View.GONE
            switch2.visibility = View.GONE
            switch3.visibility = View.GONE
            switch4.visibility = View.GONE
            switch5.visibility = View.GONE
            switch6.visibility = View.GONE
            save_brands.visibility = View.GONE
            brands_info.visibility = View.VISIBLE
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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
     * settings() is called when the settings button in the menu is pressed
     */
    fun settings(){
        val intent = Intent(activity, SettingsActivity::class.java)
        startActivity(intent)
    }
}
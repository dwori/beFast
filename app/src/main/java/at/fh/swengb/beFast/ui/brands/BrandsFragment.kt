package at.fh.swengb.beFast.ui.brands

import android.content.Context
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
import at.fh.swengb.beFast.ui.more.SettingsActivity
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.fragment_brands.*

class BrandsFragment : Fragment() {
    companion object {

        val nikeKey = "Nike"
        val adidasKey = "Adidas"
        val fearofgofKey = "Fear Of God"
        val newbalanceKey = "New Balance"
        val pumaKey = "Puma"
        val supremeKey = "Supreme"

    }

    private lateinit var brandsViewModel: BrandsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        brandsViewModel =
                ViewModelProvider(this).get(BrandsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_brands, container, false)
        val textView: TextView = root.findViewById(R.id.text_brands)
        brandsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val sharedPreferences = requireContext().getSharedPreferences(requireContext().packageName, Context.MODE_PRIVATE)

        if (sharedPreferences.getBoolean(SettingsActivity.loginBool, false)) {

            brands_info.visibility = View.GONE

            save_brands.visibility = View.VISIBLE

            switch1.isChecked = sharedPreferences.getBoolean(nikeKey, true)

            switch2.isChecked = sharedPreferences.getBoolean(adidasKey, true)

            switch3.isChecked = sharedPreferences.getBoolean(fearofgofKey, true)

            switch4.isChecked = sharedPreferences.getBoolean(newbalanceKey, true)

            switch5.isChecked = sharedPreferences.getBoolean(pumaKey, true)

            switch6.isChecked = sharedPreferences.getBoolean(supremeKey, true)

            save_brands.setOnClickListener {
                sharedPreferences.edit().putBoolean(nikeKey, switch1.isChecked).apply()
                sharedPreferences.edit().putBoolean(adidasKey, switch2.isChecked).apply()
                sharedPreferences.edit().putBoolean(fearofgofKey, switch3.isChecked).apply()
                sharedPreferences.edit().putBoolean(newbalanceKey, switch4.isChecked).apply()
                sharedPreferences.edit().putBoolean(pumaKey, switch5.isChecked).apply()
                sharedPreferences.edit().putBoolean(supremeKey, switch6.isChecked).apply()

            }

        } else {
            brands_info.visibility = View.VISIBLE
            save_brands.visibility = View.GONE
            switch1.visibility = View.GONE
            switch2.visibility = View.GONE
            switch3.visibility = View.GONE
            switch4.visibility = View.GONE
            switch5.visibility = View.GONE
            switch6.visibility = View.GONE
        }

    }
}
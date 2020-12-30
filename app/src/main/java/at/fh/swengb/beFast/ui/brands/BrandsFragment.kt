package at.fh.swengb.beFast.ui.brands

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
import at.fh.swengb.beFast.ui.more.SettingsActivity
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.fragment_brands.*

class BrandsFragment : Fragment() {
    companion object {

        val nikeKey = "NIKE"
        val adidasKey = "ADIDAS"


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
            val savedNike = sharedPreferences.getBoolean(nikeKey, false)
            switch1.isChecked = savedNike


            save_brands.setOnClickListener {
                sharedPreferences.edit().putBoolean(nikeKey, switch1.isChecked).apply()
            }

        } else {
            switch1.visibility = View.GONE
            switch2.visibility = View.GONE
            switch3.visibility = View.GONE
        }

    }
}
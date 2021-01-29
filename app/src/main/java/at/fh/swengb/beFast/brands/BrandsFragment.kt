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
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        brandsViewModel = ViewModelProvider(this).get(BrandsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_brands, container, false)
        val textView: TextView = root.findViewById(R.id.text_brands)
        brandsViewModel.text.observe(viewLifecycleOwner, Observer { textView.text = it })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        sharedPreferences = requireContext().getSharedPreferences(requireContext().packageName, Context.MODE_PRIVATE)

        //Glide.with(this).load("https://i.pinimg.com/736x/4e/b7/9c/4eb79c5e8456cb65830a6ef1faa0f688.jpg").into(imageView)
        //Glide.with(this).load("https://i.pinimg.com/originals/21/21/b5/2121b5dc445a1d0cb69965ecaecfaf80.jpg").into(imageView)

        if (sharedPreferences.getBoolean(SettingsActivity.loginBoolKey, false)) {
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

    fun settings(){
        val intent = Intent(activity, SettingsActivity::class.java)
        startActivity(intent)
    }
}
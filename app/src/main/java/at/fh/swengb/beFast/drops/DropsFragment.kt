package at.fh.swengb.beFast.drops

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_drops.*
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.brands.BrandsFragment
import at.fh.swengb.beFast.drops.recyclerview.DescriptionActivity
import at.fh.swengb.beFast.drops.recyclerview.DropsAdapter
import at.fh.swengb.beFast.drops.recyclerview.DropsRepository
import at.fh.swengb.beFast.settings.SettingsActivity


class DropsFragment : Fragment() {

    private lateinit var dropsViewModel: DropsViewModel
    lateinit var dropsAdapter: DropsAdapter

    companion object {
        val EXTRA_DROP_ID = "DROP_ID_EXTRA"
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dropsViewModel = ViewModelProvider(this).get(DropsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_drops, container, false)
        val textView: TextView = root.findViewById(R.id.text_drops)
        dropsViewModel.text.observe(viewLifecycleOwner, Observer { textView.text = it })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dropsAdapter = DropsAdapter {
            val descriptionIntent = Intent(activity, DescriptionActivity::class.java)
            descriptionIntent.putExtra(EXTRA_DROP_ID, it.id)
            startActivity(descriptionIntent)
        }
        val sharedPreferences = requireContext().getSharedPreferences(requireContext().packageName, Context.MODE_PRIVATE)
        val loginBool = sharedPreferences.getBoolean(SettingsActivity.loginBool, false)

        val savedNike = sharedPreferences.getBoolean(BrandsFragment.nikeKey, true)
        val savedAdidas = sharedPreferences.getBoolean(BrandsFragment.adidasKey, true)
        val savedFear = sharedPreferences.getBoolean(BrandsFragment.fearofgofKey, true)
        val savedNewB= sharedPreferences.getBoolean(BrandsFragment.newbalanceKey, true)
        val savedPuma = sharedPreferences.getBoolean(BrandsFragment.pumaKey, true)
        val savedSupreme = sharedPreferences.getBoolean(BrandsFragment.supremeKey, true)
        if (!savedNike && !savedAdidas && !savedFear && !savedNewB && !savedPuma && !savedSupreme){
            text_drops.visibility = View.VISIBLE
        } else {
            text_drops.visibility = View.GONE
        }
        if (loginBool) {
            dropsAdapter.updateList(DropsRepository.drops)
            if (!savedNike) {
                dropsAdapter.filterList("Nike")
                Log.e("Filter", "Nike")
            }
            if (!savedAdidas) {
                dropsAdapter.filterList("Adidas")
                Log.e("Filter", "Adidas")
            }
            if (!savedFear) {
                dropsAdapter.filterList("Fear Of God")
                Log.e("Filter", "Fear Of God")
            }
            if (!savedNewB) {
                dropsAdapter.filterList("New Balance")
                Log.e("Filter", "New Balance")
            }
            if (!savedPuma) {
                dropsAdapter.filterList("Puma")
                Log.e("Filter", "Puma")
            }
            if (!savedSupreme) {
                dropsAdapter.filterList("Supreme")
                Log.e("Filter", "Supreme")
            }
        } else {
            dropsAdapter.updateList(DropsRepository.drops)
        }
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        fragment_drops_recyclerview.layoutManager = layoutManager
        fragment_drops_recyclerview.adapter = dropsAdapter
    }
}
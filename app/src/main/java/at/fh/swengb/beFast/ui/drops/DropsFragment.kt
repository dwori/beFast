package at.fh.swengb.beFast.ui.drops

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.models.Drops
import at.fh.swengb.beFast.ui.brands.BrandsFragment
import at.fh.swengb.beFast.ui.drops.DropsRepository.drops
import at.fh.swengb.beFast.ui.news.TweetAdapter
import kotlinx.android.synthetic.main.drops_recycler_view_item.*
import kotlinx.android.synthetic.main.fragment_brands.*
import kotlinx.android.synthetic.main.fragment_drops.*
import kotlinx.android.synthetic.main.fragment_news.*
import java.text.SimpleDateFormat
import java.util.*

class DropsFragment : Fragment() {

    private lateinit var dropsViewModel: DropsViewModel
    lateinit var dropsAdapter: DropsAdapter

    companion object {
        val EXTRA_DROP_ID = "DROP_ID_EXTRA"
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dropsViewModel =
                ViewModelProvider(this).get(DropsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_drops, container, false)
        val textView: TextView = root.findViewById(R.id.text_drops)
        dropsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dropsAdapter = DropsAdapter() {
            val descriptionIntent = Intent(activity, DescriptionActivity::class.java)
            descriptionIntent.putExtra(EXTRA_DROP_ID, it.id)
            startActivity(descriptionIntent)
        }
        val sharedPreferences = requireContext().getSharedPreferences(requireContext().packageName, Context.MODE_PRIVATE)
        val savedNike = sharedPreferences.getBoolean(BrandsFragment.nikeKey, true)
        val savedAdidas = sharedPreferences.getBoolean(BrandsFragment.adidasKey, true)
        val savedFear = sharedPreferences.getBoolean(BrandsFragment.fearofgofKey, true)
        val savedNewB= sharedPreferences.getBoolean(BrandsFragment.newbalanceKey, true)
        val savedPuma = sharedPreferences.getBoolean(BrandsFragment.pumaKey, true)
        val savedSupreme = sharedPreferences.getBoolean(BrandsFragment.supremeKey, true)


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
            Log.e("Filter","Fear Of God")
        }
        if (!savedNewB) {
            dropsAdapter.filterList("New Balance")
            Log.e("Filter","New Balance")
        }
        if (!savedPuma) {
            dropsAdapter.filterList("Puma")
            Log.e("Filter","Puma")
        }
        if (!savedSupreme) {
            dropsAdapter.filterList("Supreme")
            Log.e("Filter","Supreme")
        }



        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        fragment_drops_recyclerview.layoutManager = layoutManager
        fragment_drops_recyclerview.adapter = dropsAdapter




    }
}
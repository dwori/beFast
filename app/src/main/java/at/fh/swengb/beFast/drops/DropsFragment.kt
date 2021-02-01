package at.fh.swengb.beFast.drops

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.core.content.ContextCompat
import at.fh.swengb.beFast.Menu.consume
import kotlinx.android.synthetic.main.fragment_drops.*
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.brands.BrandsFragment
import at.fh.swengb.beFast.drops.recyclerview.description.DescriptionActivity
import at.fh.swengb.beFast.drops.recyclerview.DropsAdapter
import at.fh.swengb.beFast.drops.recyclerview.DropsRepository
import at.fh.swengb.beFast.settings.SettingsActivity

/**
 * The DropsFragment displays different drops, which are called from the DropsRepository. If you are logged in,
 * you can set in the BrandsFragment which brands you want to see in this fragment.
 */


class DropsFragment : Fragment() {

    private lateinit var dropsAdapter: DropsAdapter

    companion object {
        const val EXTRA_DROP_ID = "DROP_ID_EXTRA"
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_drops, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        dropsAdapter = DropsAdapter {
            val descriptionIntent = Intent(activity, DescriptionActivity::class.java)
            descriptionIntent.putExtra(EXTRA_DROP_ID, it.id)
            startActivity(descriptionIntent)
        }
        /**
         * We get the shared Preferences in order get and put boolean keys.
         **/
        val sharedPreferences = requireContext().getSharedPreferences(requireContext().packageName, Context.MODE_PRIVATE)

        val loginBool = sharedPreferences.getBoolean(SettingsActivity.loginBoolKey, false)

        val savedNike = sharedPreferences.getBoolean(BrandsFragment.nikeKey, true)
        val savedAdidas = sharedPreferences.getBoolean(BrandsFragment.adidasKey, true)
        val savedFear = sharedPreferences.getBoolean(BrandsFragment.fearofgofKey, true)
        val savedNewB= sharedPreferences.getBoolean(BrandsFragment.newbalanceKey, true)
        val savedPuma = sharedPreferences.getBoolean(BrandsFragment.pumaKey, true)
        val savedSupreme = sharedPreferences.getBoolean(BrandsFragment.supremeKey, true)
        drops_info.visibility = View.GONE

        /**
         * if one brand gets switched off, the filterList function is called
         * and the brand is no longer in Drops until the switch is turned on again
         */
        if (loginBool) {
            if (!savedNike && !savedAdidas && !savedFear && !savedNewB && !savedPuma && !savedSupreme) {
                drops_info.visibility = View.VISIBLE
                Log.i("INFO", "SHOW drops_info")
            }
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
                dropsAdapter.filterList("Fear of God")
                Log.e("Filter", "Fear of God")
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
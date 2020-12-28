package at.fh.swengb.beFast.ui.drops

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
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
import at.fh.swengb.beFast.ui.drops.DropsAdapter.Companion.BRAND_NAME
import at.fh.swengb.beFast.ui.drops.DropsRepository.drops
import at.fh.swengb.beFast.ui.news.TweetAdapter
import kotlinx.android.synthetic.main.drops_recycler_view_item.*
import kotlinx.android.synthetic.main.fragment_drops.*
import kotlinx.android.synthetic.main.fragment_news.*
import java.util.*

class DropsFragment : Fragment() {

    private lateinit var dropsViewModel: DropsViewModel
    lateinit var dropsAdapter: DropsAdapter



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
            val cal: Calendar = Calendar.getInstance()
            val calendarIntent = Intent(Intent.ACTION_EDIT)
            calendarIntent.type = "vnd.android.cursor.item/event"
            calendarIntent.putExtra("beginTime", cal.set(2020, 9, 29).toString())
            //intent.putExtra("allDay", true)
            //intent.putExtra("rrule", "FREQ=YEARLY")
            calendarIntent.putExtra("endTime", cal.getTimeInMillis() + 60601000)
            calendarIntent.putExtra("title",BRAND_NAME)
            startActivity(calendarIntent)

        }

        dropsAdapter.updateList(DropsRepository.drops)
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        fragment_drops_recyclerview.layoutManager = layoutManager
        fragment_drops_recyclerview.adapter = dropsAdapter




    }
}
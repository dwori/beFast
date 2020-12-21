package at.fhj.ima.myapplication.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import at.fhj.ima.myapplication.R
import at.fhj.ima.myapplication.adapter.ArticleAdapter
import at.fhj.ima.myapplication.adapter.MainAdapter
import at.fhj.ima.myapplication.sampledata.ArticleRepository
import at.fhj.ima.myapplication.sampledata.ArticleRepository.articleList
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_home.*

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }

}
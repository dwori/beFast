package at.fhj.ima.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import at.fhj.ima.myapplication.adapter.MainAdapter
import at.fhj.ima.myapplication.R
import at.fhj.ima.myapplication.sampledata.ArticleRepository
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {




    private lateinit var homeViewModel: HomeViewModel
    private lateinit var itemsAdapter: MainAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })


        return root


    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(homeViewModel::class.java)

        // yes it works
        init()



    }


    private fun init() {

        itemsAdapter = MainAdapter(activity)

        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvItems.layoutManager = layoutManager
        rvItems.adapter = itemsAdapter
    }
}

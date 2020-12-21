package at.fhj.ima.myapplication.ui.notifications

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
import at.fhj.ima.myapplication.sampledata.ArticleRepository
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var itemsAdapter: ArticleAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        notificationsViewModel = ViewModelProvider(this).get(notificationsViewModel::class.java)

        // yes it works
        init()



    }


    private fun init() {

        itemsAdapter = ArticleAdapter()
        itemsAdapter.updateList(ArticleRepository.articleList())
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        lesson_recycler_view.layoutManager = layoutManager
        lesson_recycler_view.adapter = itemsAdapter
    }
}
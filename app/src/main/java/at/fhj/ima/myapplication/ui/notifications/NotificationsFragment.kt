package at.fhj.ima.myapplication.ui.notifications

import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import at.fhj.ima.myapplication.R
import at.fhj.ima.myapplication.adapter.ArticleAdapter
import at.fhj.ima.myapplication.adapter.ProductsAdapter
import at.fhj.ima.myapplication.models.Article
import at.fhj.ima.myapplication.sampledata.ArticleRepository
import com.google.gson.Gson
import com.google.gson.internal.bind.TypeAdapters.URL
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_notifications.*
import kotlinx.android.synthetic.main.fragment_notifications.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

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
        parseJson()



    }


    private fun init() {
        itemsAdapter = ArticleAdapter()
        ArticleRepository.articleList(
                success = {
                    // handle success
                    itemsAdapter.updateList(it)
                },
                error = {
                    // handle error
                    Log.e("Error","Repository Error123")
                }
        )
        parseJson()
        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        lesson_recycler_view.layoutManager = layoutManager
        lesson_recycler_view.adapter = itemsAdapter
    }
    fun parseJson() {
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter<Article>(Article::class.java)
        val article = jsonAdapter.fromJson("""
            {
            "text": "Ad: #XboxSeries  on Bestbuy\n\nS:https://t.co/nlWpjw7P7m\nX:https://t.co/vuTjBfvdDw\nBundles:https://t.co/wcElFRgyKi",
            "created_at": "Mon Dec 21 18:23:16 +0000 2020"

        }
        """.trimIndent())
    }
}
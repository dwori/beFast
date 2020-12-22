package at.fh.swengb.beFast.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.SleepyAsyncTask
import at.fh.swengb.beFast.models.TweetsItem
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {

    private lateinit var newsViewModel: NewsViewModel
    private lateinit var tweetAdapter: TweetAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        newsViewModel =
                ViewModelProvider(this).get(NewsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_news, container, false)
        val textView: TextView = root.findViewById(R.id.text_news)
        newsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }
    private fun init() {
        SleepyAsyncTask()
        tweetAdapter = TweetAdapter()

        TweetRepository.tweetList(
                success = {
                    // handle success
                    tweetAdapter.updateList(it)
                },
                error = {
                    // handle error
                    Log.e("Error","Repository Error123")
                }
        )
        parseJson()

        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        fragment_recycler_view_news.layoutManager = layoutManager
        fragment_recycler_view_news.adapter = tweetAdapter
    }
    fun parseJson() {
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter<TweetsItem>(TweetsItem::class.java)
        val tweet = jsonAdapter.fromJson("""
            {
            "text": "Ad: #XboxSeries  on Bestbuy\n\nS:https://t.co/nlWpjw7P7m\nX:https://t.co/vuTjBfvdDw\nBundles:https://t.co/wcElFRgyKi",
            "entities": {
            "urls": [
                {
                    "url": "https://t.co/yQozzfLIx6"
                    
                },
                {
                    "url": "https://t.co/A2uIv8wmFP"
                 }
            ]
            }
        }   
            
            
       
            
        
        """.trimIndent())
    }
}
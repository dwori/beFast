package at.fh.swengb.beFast.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.models.TweetsItem
import kotlinx.android.synthetic.main.news_recycler_view_item.view.*
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class TweetAdapter( val clickListener: (tweet: TweetsItem) -> Unit ): RecyclerView.Adapter<TweetViewHolder>() {
    private var tweetList = listOf<TweetsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val tweetItemView = LayoutInflater.from(parent.context).inflate(R.layout.news_recycler_view_item, parent, false)
        return TweetViewHolder(tweetItemView,clickListener)
    }
    override fun onBindViewHolder(holder: TweetViewHolder,position: Int) {
        holder.bindItem(tweetList[position])
    }
    override fun getItemCount(): Int = tweetList.size

    fun updateList(newList: List<TweetsItem>){
        tweetList = newList
        notifyDataSetChanged()
    }

}
class TweetViewHolder(itemView: View, val clickListener: (tweet: TweetsItem) -> Unit ): RecyclerView.ViewHolder(itemView){
    fun bindItem(tweet: TweetsItem) {
        if (tweet.entities.media != null && tweet.entities.media.isNotEmpty()) {
            itemView.item_tweet_url.text = tweet.entities.media[0].media_url_https

            Glide.with(itemView)
                .load(tweet.entities.media[0].media_url_https)
                .fitCenter()
                .into(itemView.item_tweet_image)


        } else if (tweet.entities.media[0].media_url_https == " ") {

        }

        itemView.item_tweet_text.text = tweet.text
        if (tweet.entities.urls != null && tweet.entities.urls.isNotEmpty()) {
            //itemView.item_tweet_url.text = tweet.entities.urls[0].url
            itemView.setOnClickListener{
                clickListener(tweet)
            }
        } else {
            // itemView.item_tweet_url.text = "no link"
        }
        // time zones
        val date: String = tweet.created_at
        val sdf = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
        val formattedDate = sdf.parse(date)
        sdf.timeZone = TimeZone.getDefault()
        itemView.item_tweet_time.text = sdf.format(formattedDate).substring(11,16)
    }
}
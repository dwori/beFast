package at.fh.swengb.beFast.news.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.news_recycler_view_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import at.fh.swengb.beFast.models.tweets.TweetsItem


/**
 * This TweetViewHolder is used by the TweetAdapter
 *
 * This class describes an item view and metadata about its place within the RecyclerView.
 * Used by the adapter to fill one View with data for one list item (ViewHolders belong to the adapter)
 * Is positioned by the layout manager
 * custom ViewHolder implementations to store data that makes binding view contents
 * Can hold (almost) any type of view
 *
 * @param itemView uses a limited number of view items
 * @param clickListener is used so that elements are clickable
 */

class TweetViewHolder(itemView: View, val clickListener: (tweet: TweetsItem) -> Unit ): RecyclerView.ViewHolder(itemView){
    fun bindItem(tweet: TweetsItem) {
        /**
         * format the date string that represents the time the tweet was posted, according to the timezone the phone is in
         */
        val sdf = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
        val date: Date? = sdf.parse(tweet.created_at)
        sdf.timeZone = TimeZone.getDefault()
        itemView.item_tweet_time.text = sdf.format(date!!).substring(11,16)
        itemView.item_tweet_text.text = tweet.text
        if (tweet.entities.media.isNotEmpty()) {
            Glide.with(itemView).load(tweet.entities.media[0].media_url_https).fitCenter().into(itemView.item_tweet_image)
        }
        if (tweet.entities.urls.isNotEmpty()) {
            itemView.setOnClickListener { clickListener(tweet) }
        }
    }
}
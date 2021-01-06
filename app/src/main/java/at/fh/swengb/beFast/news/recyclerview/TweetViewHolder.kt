package at.fh.swengb.beFast.news.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.news_recycler_view_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import at.fh.swengb.beFast.models.tweets.TweetsItem

class TweetViewHolder(itemView: View, val clickListener: (tweet: TweetsItem) -> Unit ): RecyclerView.ViewHolder(itemView){
    fun bindItem(tweet: TweetsItem) {
        if (tweet.entities.media.isNotEmpty()) {
            //itemView.item_tweet_url.text = tweet.entities.media[0].media_url_https

            Glide.with(itemView)
                    .load(tweet.entities.media[0].media_url_https)
                    .fitCenter()
                    .into(itemView.item_tweet_image)


        } else if (tweet.entities.media[0].media_url_https == " ") {
         // todo
        }

        itemView.item_tweet_text.text = tweet.text
        if (tweet.entities.urls.isNotEmpty()) {
            //itemView.item_tweet_url.text = tweet.entities.urls[0].url
            itemView.setOnClickListener{
                clickListener(tweet)
            }
        } else {
            // itemView.item_tweet_url.text = "no link"
            // todo
        }
        // time zones
        val date: String = tweet.created_at
        val sdf = SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
        val formattedDate = sdf.parse(date)
        sdf.timeZone = TimeZone.getDefault()
        itemView.item_tweet_time.text = sdf.format(formattedDate!!).substring(11,16)
    }
}
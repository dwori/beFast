package at.fh.swengb.beFast.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.models.TweetsItem
import kotlinx.android.synthetic.main.news_recycler_view.view.*
import com.bumptech.glide.Glide

class TweetAdapter( val clickListener: (tweet: TweetsItem) -> Unit ): RecyclerView.Adapter<TweetViewHolder>() {
    private var tweetList = listOf<TweetsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val tweetItemView = LayoutInflater.from(parent.context).inflate(R.layout.news_recycler_view, parent, false)
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
        // Glide.with(itemView).load(tweet.entities.media[0].media_url_https).into(itemView.item_tweet_image)
        itemView.item_tweet_text.text = tweet.text
        if (tweet.entities.urls != null && tweet.entities.urls.isNotEmpty()) {
            //itemView.item_tweet_url.text = tweet.entities.urls[0].url
            itemView.setOnClickListener{
                clickListener(tweet)
            }
        } else {
            // itemView.item_tweet_url.text = "no link"
        }
        itemView.item_tweet_time.text = tweet.created_at


    }
}
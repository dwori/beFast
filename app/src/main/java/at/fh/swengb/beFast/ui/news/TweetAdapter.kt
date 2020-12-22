package at.fh.swengb.beFast.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.models.TweetsItem
import kotlinx.android.synthetic.main.news_recycler_view.view.*

class TweetAdapter: RecyclerView.Adapter<TweetViewHolder>() {
    private var tweetList = listOf<TweetsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val tweetItemView = LayoutInflater.from(parent.context).inflate(R.layout.news_recycler_view, parent, false)
        return TweetViewHolder(tweetItemView)
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
class TweetViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun bindItem(tweet: TweetsItem) {
        itemView.item_tweet_text.text = tweet.text
        // itemView.
    }
}
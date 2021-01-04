package at.fh.swengb.beFast.news.recylcerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.models.TweetsItem

class TweetAdapter( val clickListener: (tweet: TweetsItem) -> Unit ): RecyclerView.Adapter<TweetViewHolder>() {
    private var tweetList = listOf<TweetsItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val tweetItemView = LayoutInflater.from(parent.context).inflate(R.layout.news_recycler_view_item, parent, false)
        return TweetViewHolder(tweetItemView,clickListener)
    }
    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        holder.bindItem(tweetList[position])
    }
    override fun getItemCount(): Int = tweetList.size

    fun updateList(newList: List<TweetsItem>){
        tweetList = newList
        notifyDataSetChanged()
    }

}

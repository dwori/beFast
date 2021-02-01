package at.fh.swengb.beFast.news.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.models.tweets.TweetsItem


/**
 * This TweetAdapter is called in the NewsFragment.
 *
 * This class connects data with view items.
 * First it receives data, then makes data displayable in a view and adds data to the view.
 * Manages creating, updating, adding, deleting View items as underlying data changes.
 * @param clickListener is used so that i the fragment it can be filled with a lambda that holds the intent for the clickListener.
 */
class TweetAdapter(private val clickListener: (tweet: TweetsItem) -> Unit ): RecyclerView.Adapter<TweetViewHolder>() {

    var tweetList = listOf<TweetsItem>()

    /**
     * Inflates a view and returns a ViewHolder that contains it.
     * Recycler View needs a new ViewHolder to represent an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val tweetItemView = LayoutInflater.from(parent.context).inflate(R.layout.news_recycler_view_item, parent, false)
        return TweetViewHolder(tweetItemView,clickListener)
    }

    /**
     * Sets the contents of a view at a given position.
     * Called when a View needs to be filled with data.
     */
    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        holder.bindItem(tweetList[position])
    }

    /**
     * @return the total number of Items held in the Adapter.
     */
    override fun getItemCount(): Int = tweetList.size

    /**
     * this function sets the var tweetList to the input newList.
     */
    fun updateList(newList: List<TweetsItem>){
        tweetList = newList
        notifyDataSetChanged()
    }
}
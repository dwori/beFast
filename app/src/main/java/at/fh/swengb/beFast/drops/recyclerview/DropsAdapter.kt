package at.fh.swengb.beFast.drops.recyclerview

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.models.drops.Drops
/**
 * This DropsAdapter is called in the DropsFragment.
 *
 * This class connects data with view items.
 * First it receives data, then makes data displayable in a view and adds data to the view.
 * Manages creating, updating, adding, deleting View items as underlying data changes.
 * @param clickListener is used so that in the fragment it can be filled with a lambda that holds the intent for the clickListener.
 */

class DropsAdapter(private val clickListener: (drop: Drops) -> Unit): RecyclerView.Adapter<DropViewHolder>() {

    var dropList = listOf<Drops>()
    /**
     * Inflates a view and returns a ViewHolder that contains it.
     * Recycler View needs a new ViewHolder to represent an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DropViewHolder {
        val dropItemView = LayoutInflater.from(parent.context).inflate(
                R.layout.drops_recycler_view_item,
                parent,
                false
        )
        return DropViewHolder(dropItemView, clickListener)
    }
    /**
     * @return the total number of Items held in the Adapter.
     */
    override fun getItemCount(): Int = dropList.size
    /**
     * Sets the contents of a view at a given position.
     * Called when a View needs to be filled with data.
     */
    override fun onBindViewHolder(holder: DropViewHolder, position: Int) {
        holder.bindItem(dropList[position])
    }
    /**
     * @param newList is used to set the var dropList
     */
    fun updateList(newList: List<Drops>) {
        dropList = newList
        notifyDataSetChanged()
    }

    /**
     * @param string is used to remove all items from the Drops List which have a specific brand
     */
    fun filterList(string: String) {
        dropList = dropList.filter { it.brand != string }
        notifyDataSetChanged()
    }
}
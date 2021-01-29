package at.fh.swengb.beFast.drops.recyclerview

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.models.drops.Drops


class DropsAdapter(private val clickListener: (drop: Drops) -> Unit): RecyclerView.Adapter<DropViewHolder>() {

    var dropList = listOf<Drops>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DropViewHolder {
        val dropItemView = LayoutInflater.from(parent.context).inflate(
                R.layout.drops_recycler_view_item,
                parent,
                false
        )
        return DropViewHolder(dropItemView, clickListener)
    }

    override fun getItemCount(): Int = dropList.size

    override fun onBindViewHolder(holder: DropViewHolder, position: Int) {
        holder.bindItem(dropList[position])
    }

    fun updateList(newList: List<Drops>) {
        dropList = newList
        notifyDataSetChanged()
    }
    //filterList is used to find all items the Drops List which have a specific brand
    fun filterList(string: String) {
        dropList = dropList.filter { it.brand != string }
        notifyDataSetChanged()
    }
}
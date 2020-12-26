package at.fh.swengb.beFast.ui.drops

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import at.fh.swengb.beFast.R
import at.fh.swengb.beFast.models.Drops
import at.fh.swengb.beFast.models.TweetsItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.drops_recycler_view_item.view.*
import kotlinx.android.synthetic.main.news_recycler_view_item.view.*
import java.util.*

class DropsAdapter(val clickListener: (drop: Drops) -> Unit): RecyclerView.Adapter<DropViewHolder>() {

    private var dropList = listOf<Drops>()

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
}

class DropViewHolder(itemView: View, val clickListener: (drop: Drops) -> Unit): RecyclerView.ViewHolder(itemView) {
    fun bindItem(drop: Drops) {
        itemView.drop_item_brand.text = drop.brand
        itemView.drop_item_name.text = drop.name
        itemView.drop_item_price.text = drop.price
        itemView.drop_item_date.text = drop.date
        itemView.drop_item_time.text = drop.time
        Glide.with(itemView)
            .load(drop.imageUrl)
            .fitCenter()
            .into(itemView.drop_item_image)
        itemView.drop_item_reminder.setOnClickListener {
            clickListener(drop)

        }
    }
}
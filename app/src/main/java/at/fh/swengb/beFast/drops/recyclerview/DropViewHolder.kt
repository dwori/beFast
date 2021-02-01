package at.fh.swengb.beFast.drops.recyclerview

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.drops_recycler_view_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import at.fh.swengb.beFast.models.drops.Drops
/**
 * This DropViewHolder is used by the DropsAdapter
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
class DropViewHolder(itemView: View, val clickListener: (drop: Drops) -> Unit): RecyclerView.ViewHolder(itemView) {
    fun bindItem(drop: Drops) {

        itemView.drop_item_brand.text = drop.brand
        itemView.drop_item_name.text = drop.name
        itemView.drop_item_price.text = drop.price

        /**
         * formatting time zones with SimpleDateFormat library
         */
        val sdf = SimpleDateFormat("dd.MM.yyyy z HH:mm", Locale.ENGLISH)
        val date = sdf.parse(drop.datetime)
        sdf.timeZone = TimeZone.getDefault()
        itemView.drop_item_datetime.text = sdf.format(date!!)

        Glide.with(itemView)
                .load(drop.imageUrl)
                .fitCenter()
                .into(itemView.drop_item_image)
        itemView.setOnClickListener {
            clickListener(drop)
        }
    }
}
package at.fh.swengb.beFast.drops.recylcerview

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.drops_recycler_view_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import at.fh.swengb.beFast.models.drops.Drops

class DropViewHolder(itemView: View, val clickListener: (drop: Drops) -> Unit): RecyclerView.ViewHolder(itemView) {
    fun bindItem(drop: Drops) {

        itemView.drop_item_brand.text = drop.brand
        itemView.drop_item_name.text = drop.name
        itemView.drop_item_price.text = drop.price

        // time zones
        val date: String = drop.datetime
        val sdf = SimpleDateFormat("dd.MM.yyyy z HH:mm", Locale.ENGLISH)
        val formattedDate = sdf.parse(date)
        sdf.timeZone = TimeZone.getDefault()
        itemView.drop_item_datetime.text = sdf.format(formattedDate!!)

        // set image
        Glide.with(itemView)
                .load(drop.imageUrl)
                .fitCenter()
                .into(itemView.drop_item_image)
        itemView.setOnClickListener {
            clickListener(drop)
        }
    }
}

package at.fhj.ima.myapplication.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import at.fhj.ima.myapplication.models.Article
import kotlinx.android.synthetic.main.layout_header_item.view.*
import kotlinx.android.synthetic.main.layout_items.view.*

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)

}
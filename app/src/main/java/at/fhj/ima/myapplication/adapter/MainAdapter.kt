package at.fhj.ima.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import at.fhj.ima.myapplication.R
import at.fhj.ima.myapplication.models.Article
import kotlinx.android.synthetic.main.layout_header.view.*
import kotlinx.android.synthetic.main.layout_items.view.*

class MainAdapter(private val context: Context?) : RecyclerView.Adapter<BaseViewHolder<*>>() {


    private var articleList = listOf<Article>()

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEMS = 1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TYPE_HEADER -> {
                val view = LayoutInflater.from(context).inflate(R.layout.layout_header, parent, false)
                HeaderViewHolder(view)
            }

            TYPE_ITEMS -> {
                val view = LayoutInflater.from(context).inflate(R.layout.layout_items, parent, false)
                ItemViewHolder(view)
            }
            else -> throw IllegalArgumentException("invalid view type")
        }
    }

    override fun getItemCount(): Int {
        return 10
    }


    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {

    }




    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_HEADER
        }

        return TYPE_ITEMS
    }


    inner class HeaderViewHolder(itemView: View) : BaseViewHolder<View>(itemView) {

        init {
            val recentPurchaseAdapter = HeaderAdapter(context!!)
            val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            itemView.rvHeader.layoutManager = layoutManager
            itemView.rvHeader.adapter = recentPurchaseAdapter
        }

        override fun bind(item: View) {


        }
    }

    class ItemViewHolder(itemView: View) : BaseViewHolder<View>(itemView) {

        override fun bind(item: View) {


        }
    }

}
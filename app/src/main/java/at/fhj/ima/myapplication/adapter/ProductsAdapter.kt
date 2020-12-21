package at.fhj.ima.myapplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import at.fhj.ima.myapplication.R
import at.fhj.ima.myapplication.models.Article
import kotlinx.android.synthetic.main.recycler_view.view.*

class ProductsAdapter(private val products: List<Article>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
        val product = products[position]
        holder.name.text = product.text
        holder.date.text = product.created_at
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view, parent, false)
        val holder = ViewHolder(view)
        return holder
    }

    override fun getItemCount() = products.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.item_article_name)
        val date: TextView = itemView.findViewById(R.id.item_article_date)
    }
}

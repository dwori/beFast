package at.fhj.ima.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.fhj.ima.myapplication.R
import at.fhj.ima.myapplication.models.Article

class HeaderAdapter(private val context: Context) : RecyclerView.Adapter<HeaderAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_header_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }


    // View Holder
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}
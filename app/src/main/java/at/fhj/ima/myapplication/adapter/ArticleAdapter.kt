package at.fhj.ima.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import at.fhj.ima.myapplication.R
import at.fhj.ima.myapplication.models.Article
import kotlinx.android.synthetic.main.recycler_view.view.*

class ArticleAdapter: RecyclerView.Adapter<LessonViewHolder>() {


    private var articleList = listOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val lessonItemView = inflater.inflate(R.layout.recycler_view, parent, false)
        return LessonViewHolder(lessonItemView)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
     holder.bindItem(articleList[position])
    }

    fun updateList(newList: List<Article>) {
        articleList = newList
        notifyDataSetChanged()
    }
}

class LessonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    fun bindItem(article: Article) {
        itemView.item_article_name.text = article.text
        itemView.item_article_date.text = article.created_at
    }
}
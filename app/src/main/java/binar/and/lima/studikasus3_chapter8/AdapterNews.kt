package binar.and.lima.studikasus3_chapter8

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import binar.and.lima.studikasus3_chapter8.model.GetAllNewsResponse
import binar.and.lima.studikasus3_chapter8.model.GetAllNewsResponseItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_news.view.*

class AdapterNews (private val listDataNews: List<GetAllNewsResponseItem>,private var onClik : (GetAllNewsResponseItem)->Unit):RecyclerView.Adapter<AdapterNews.ViewHolder>() {
    class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewitem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news,parent, false)
        return ViewHolder(viewitem)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.cardNews.setOnClickListener {
            onClik(listDataNews[position])
        }

        holder.itemView.title_news.text = listDataNews[position].title
        holder.itemView.writer_news.text = listDataNews[position].author
        holder.itemView.date_news.text = listDataNews[position].createdAt

        Glide.with(holder.itemView.context).load(listDataNews[position].image).into(holder.itemView.image_news)


    }

    override fun getItemCount(): Int {
        return listDataNews.size
    }
}
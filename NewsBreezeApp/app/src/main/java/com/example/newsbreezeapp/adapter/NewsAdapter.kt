package com.example.newsbreezeapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsbreezeapp.NewsAdapter.NewsViewHolder.Companion.count1

import kotlinx.android.synthetic.main.news_item_layout.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsAdapter (val resList:List<ArticlesModel>):RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){
    class NewsViewHolder(view:View):RecyclerView.ViewHolder(view){
        companion object {
            var count1 = 0
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item_layout,parent,false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val result =resList[position]
        holder.itemView.tvNews_Title.text=result.title.toString()
        holder.itemView.tvNews_Description.text=result.description.toString()
        holder.itemView.tvNews_date.text=result.publishedAt.toString()
        Glide.with(holder.itemView.iv_image).load(result.urlToImage.toString()).into(holder.itemView.iv_image)
       val newsDatabase=NewsDatabase.getDatabase(holder.itemView.context).getNewsDao()
        holder.itemView.bookmark.setOnClickListener {
            if (count1 == 0) {
                count1++
                CoroutineScope(Dispatchers.IO).launch {
                    val newsEntity = NewsEntity(
                        result.title.toString(),
                        position.toString(),
                        result.source?.name.toString(),
                        result.publishedAt.toString(),
                        result.urlToImage.toString()
                    )
                    newsDatabase.addNewsDetails(newsEntity);
                }
                holder.itemView.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_white)
            } else if (count1 == 1) {
                count1--
                CoroutineScope(Dispatchers.IO).launch {
            }
                holder.itemView.bookmark.setImageResource(R.drawable.ic_baseline_bookmark_border_24)

            }
        }
        holder.itemView.setOnClickListener {
            val intent=Intent(holder.itemView.context,NewsDetailsActivity::class.java);
            intent.putExtra("title",result.title.toString())
            intent.putExtra("author",result.author.toString())
            intent.putExtra("description",result.description.toString())
            intent.putExtra("publishedAt",result.publishedAt.toString())
            intent.putExtra("urlToImage",result.urlToImage.toString())
            intent.putExtra("id", result.source?.id.toString())
            intent.putExtra("content",result.content.toString())
            holder.itemView.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
       return resList.size
    }

}
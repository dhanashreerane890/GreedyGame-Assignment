package com.example.newsbreezeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import kotlinx.android.synthetic.main.news_saved_item_layout.view.*

class NewsSavedAdapter(val resList:List<NewsEntity>): RecyclerView.Adapter<NewsSavedAdapter.NewsSavedViewHolder>() {

    class NewsSavedViewHolder(view: View):RecyclerView.ViewHolder(view){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsSavedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_saved_item_layout,parent,false)
        return NewsSavedAdapter.NewsSavedViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsSavedViewHolder, position: Int) {
        val saved_result =resList[position]
        holder.itemView.tv_type.text=saved_result.name.toString()
        holder.itemView.tv_Title.text=saved_result.title.toString()
        holder.itemView.tv_publishDate.text=saved_result.publishedAt.toString()
        Glide.with(holder.itemView.iv_image_saved).load(saved_result.urlToImage.toString()).into(holder.itemView.iv_image_saved)
    }

    override fun getItemCount(): Int {
     return resList.size
    }
}
package com.example.newsbreezeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_news_details.*
import kotlinx.android.synthetic.main.news_saved_item_layout.*


class NewsDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        getDataFromIntent();
        iv_back_arrow.setOnClickListener {
            onBackPressed()
        }
        iv_save.setOnClickListener {

        }
    }
    private fun getDataFromIntent() {
        if (intent!=null&&intent.extras!=null){
            val title=intent.getStringExtra("title")
            val description=intent.getStringExtra("description")
            val author =intent.getStringExtra("author")
            val id =intent.getStringExtra("id")
            val publishedAt=intent.getStringExtra("publishedAt")
            val urlToImage=intent.getStringExtra("urlToImage")
            val content=intent.getStringExtra("content")
              tv_description.text=description;
            tv_date.text=publishedAt
            Glide.with(this).load(urlToImage).into(iv_image_details);
            tvContent.text=content
            tv_id.text =id
            tv_auther_Name.text=author

        }
    }
}
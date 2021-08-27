package com.example.newsbreezeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager


import kotlinx.android.synthetic.main.activity_news_saved.*

class NewsSavedActivity : AppCompatActivity() {
    lateinit var newsSavedAdapter: NewsSavedAdapter
    val resNewsSavedList: MutableList<NewsEntity> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_saved)
        val newsDatabase= NewsDatabase.getDatabase(this).getNewsDao()

        newsSavedAdapter = NewsSavedAdapter(resNewsSavedList)
        rv_saved_recycler.layoutManager = LinearLayoutManager(this)
        rv_saved_recycler.adapter = newsSavedAdapter

        newsDatabase.getAllNewsDetails().observe(this, Observer {
            resNewsSavedList.addAll(it)
            newsSavedAdapter.notifyDataSetChanged()
        })
    }
}
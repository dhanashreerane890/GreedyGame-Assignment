package com.example.newsbreezeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val resNewsList: MutableList<ArticlesModel> = mutableListOf()
    lateinit var newsAdapter: NewsAdapter
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsAdapter = NewsAdapter(resNewsList)
        rv_recyclerView.layoutManager = LinearLayoutManager(this)
        rv_recyclerView.adapter = newsAdapter

        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        ll_bookmark.setOnClickListener {
            val intent= Intent(this, NewsSavedActivity::class.java)
            startActivity(intent)
        }
        viewModel.getMyNewsList("cricket")
        var job: Job? = null
        et_searchNews.addTextChangedListener { editable->
            job?.cancel()
            job= MainScope().launch {
                delay(500)
                editable?.let {
                    if(editable.toString().isNotEmpty()) {
                        Log.d("TAG", "onCreate: "+editable.toString())
                        viewModel.getMyNewsList(editable.toString())

                    }
                }
            }
        }
        viewModel.searchNews.observe(this@MainActivity, Observer { response ->
            resNewsList.clear()
            resNewsList.addAll(response.data?.articles as MutableList<ArticlesModel>)
            newsAdapter.notifyDataSetChanged()
        })

    }

}
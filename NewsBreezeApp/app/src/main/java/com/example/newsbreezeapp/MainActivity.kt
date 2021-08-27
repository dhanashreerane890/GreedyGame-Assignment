package com.example.newsbreezeapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsbreezeapp.database.PreferenceHelper
import com.example.newsbreezeapp.viewmodel.NewsViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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


        val preferences=PreferenceHelper
        preferences.getSharedPreferences(this);
        newsAdapter = NewsAdapter(resNewsList)
        rv_recyclerView.layoutManager = LinearLayoutManager(this)
        rv_recyclerView.adapter = newsAdapter

        viewModel = ViewModelProviders.of(this).get(NewsViewModel::class.java)
        viewModel.getMyNewsList("cricket")
        if (checkConnection(this)) {

            viewModel.searchNews.observe(this@MainActivity, Observer { response ->
                resNewsList.clear()
                resNewsList.addAll(response.data?.articles as MutableList<ArticlesModel>)
                val conToGson = Gson().toJson(resNewsList)
                preferences.writeDataToPreference(preferences.USER_ID, conToGson);
                newsAdapter.notifyDataSetChanged()
            })

        }else{
            val str=   preferences.getDataToPreference(preferences.USER_ID)
            val type= object : TypeToken<List<ArticlesModel>?>() {}.type
            val data: List<ArticlesModel> = Gson().fromJson(str, type)
            resNewsList.clear()
            resNewsList.addAll(data)
            newsAdapter.notifyDataSetChanged()
        }

        ll_bookmark.setOnClickListener {
            val intent= Intent(this, NewsSavedActivity::class.java)
            startActivity(intent)
        }
        var job: Job? = null
        et_searchNews.addTextChangedListener { editable->
            job?.cancel()
            job= MainScope().launch {
                delay(500)
                editable?.let {
                    if(editable.toString().isNotEmpty()) {
                        Log.d("TAG", "onCreate: " + editable.toString())
                        viewModel.getMyNewsList(editable.toString())

                    }
                }
            }

        }

    }
    fun checkConnection(context: Context): Boolean {
        val connMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connMgr.activeNetworkInfo
        if (activeNetworkInfo != null) { // connected to the internet
//            Toast.makeText(context, activeNetworkInfo.typeName, Toast.LENGTH_SHORT).show()
            if (activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI) {
                // connected to wifi
                return true
            } else if (activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                // connected to the mobile provider's data plan
                return true
            }
        }
        return false
    }


}
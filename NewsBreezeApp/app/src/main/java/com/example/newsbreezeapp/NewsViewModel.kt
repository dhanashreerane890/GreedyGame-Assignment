package com.example.newsbreezeapp

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel:ViewModel() {
    val repository =NewsRepository()
    val searchNews: MutableLiveData<Resource<ResponseModel>> = MutableLiveData()
    fun getMyNewsList(q:String)=viewModelScope.launch {
    val result =repository.getNewsList(q)
    searchNews.postValue(result)
    }
}
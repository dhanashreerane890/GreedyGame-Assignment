package com.example.newsbreezeapp.viewmodel

import androidx.lifecycle.*
import com.example.newsbreezeapp.Resource
import com.example.newsbreezeapp.ResponseModel
import com.example.newsbreezeapp.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel:ViewModel() {
    val repository = NewsRepository()
    val searchNews: MutableLiveData<Resource<ResponseModel>> = MutableLiveData()
    fun getMyNewsList(q:String)=viewModelScope.launch {
    val result =repository.getNewsList(q)
    searchNews.postValue(result)
    }
}
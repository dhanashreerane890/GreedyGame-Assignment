package com.example.newsbreezeapp.repository

import com.example.newsbreezeapp.*


class NewsRepository {
    val apiClient = RetrofitGenerator.getInstance().create(ApiClient::class.java)
    val responseHandler = ResponseHandler()

    suspend fun getNewsList(q:String): Resource<ResponseModel> {
        val result =apiClient.getAllNewsList(q,"7b06f3a757174bd5a5fe45d32b40aab2")
        return responseHandler.handleSuccess(result)
    }
}
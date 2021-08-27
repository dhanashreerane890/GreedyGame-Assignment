package com.example.newsbreezeapp


import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {
    @GET("v2/everything")
    suspend fun getAllNewsList(@Query("q")q:String,@Query("apiKey")apiKey:String): ResponseModel
}
//?q=bitcoin&
// apikey=7b06f3a757174bd5a5fe45d32b40aab2

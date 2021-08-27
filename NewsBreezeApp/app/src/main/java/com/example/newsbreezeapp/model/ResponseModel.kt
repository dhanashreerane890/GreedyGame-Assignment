package com.example.newsbreezeapp


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ResponseModel(

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesModel?>? = null
)
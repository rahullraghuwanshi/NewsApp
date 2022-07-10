package com.intern.assignment.data.api

import com.intern.assignment.data.models.NewsResponse
import com.intern.assignment.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getCategoryNews(
        @Query("page") pageNumber: Int = 1,
        @Query("pageSize") pageSize: Int = 10,
        @Query("country") countryCode: String = "in",
        @Query("category") category: String = "business",
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>
}
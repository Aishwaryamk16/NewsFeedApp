package com.example.newsfeedapp.data

import com.example.newsfeedapp.ArticleResponse

interface NewsApi {
    @GET("articlesearch.json")
    suspend fun getArticles(
        @Query("page") page: Int,
        @Query("q") query: String,
        @Query("api-key") apiKey: String = "pf6FgeMTQXi38BAFb9voVvHtrEQlwUlp"
    ): Response<ArticleResponse>
}
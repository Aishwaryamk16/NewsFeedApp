package com.example.newsfeedapp

data class ArticleResponse(val response: ArticleList)
data class ArticleList(val docs: List<Article>)
data class Article(
    val headline: Headline,
    val snippet: String,
    val multimedia: List<Media>,
    val web_url: String
)
data class Headline(val main: String)
data class Media(val url: String)

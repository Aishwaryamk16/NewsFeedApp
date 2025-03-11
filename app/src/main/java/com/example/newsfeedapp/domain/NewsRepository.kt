package com.example.newsfeedapp.domain

import com.example.newsfeedapp.Article
import com.example.newsfeedapp.data.ArticleDao
import com.example.newsfeedapp.data.ArticleEntity
import com.example.newsfeedapp.data.BookmarkDao
import com.example.newsfeedapp.data.NewsApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApi,
    private val articleDao: ArticleDao,
    private val bookmarkDao: BookmarkDao

) {
    suspend fun fetchArticles(page: Int, query: String): List<Article> {
        val response = api.getArticles(page, query)
        val articles = response.body()?.response?.docs ?: emptyList()

        // Cache articles locally
        articleDao.clearArticles()
        articleDao.insertArticles(articles.map {
            ArticleEntity(it.web_url, it.headline.main, it.snippet, it.multimedia.firstOrNull()?.url)
        })

        return articles
    }

    suspend fun getCachedArticles(): List<Article> {
        return articleDao.getCachedArticles().map { it.toArticle() }
    }

    //For adding bookmarks
    suspend fun addBookmark(article: ArticleEntity) {
        bookmarkDao.insertBookmark(article)
    }

    fun getBookmarks(): Flow<List<ArticleEntity>> {
        return bookmarkDao.getBookmarks()
    }

    suspend fun removeBookmark(url: String) {
        bookmarkDao.deleteBookmark(url)
    }
}
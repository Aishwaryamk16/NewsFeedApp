package com.example.newsfeedapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsfeedapp.Article

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey val web_url: String,
    val title: String,
    val snippet: String,
    val imageUrl: String?,
//For bookmark feature enable a boolean value
    val isBookmarked: Boolean = false

) {
    fun toArticle(): Article = Article(
        headline = com.example.newsfeedapp.Headline(title),
        snippet = snippet,
        multimedia = listOf(com.example.newsfeedapp.Media(imageUrl ?: "")),
        web_url = web_url
    )
}

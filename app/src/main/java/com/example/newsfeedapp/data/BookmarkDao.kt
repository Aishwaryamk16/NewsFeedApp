package com.example.newsfeedapp.data

import com.example.newsfeedapp.data.ArticleEntity

@Dao
interface BookmarkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(article: ArticleEntity)

    @Query("SELECT * FROM bookmarked_articles")
    fun getBookmarks(): Flow<List<ArticleEntity>>

    @Query("DELETE FROM bookmarked_articles WHERE web_url = :url")
    suspend fun deleteBookmark(url: String)
}
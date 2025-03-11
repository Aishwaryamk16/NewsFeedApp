package com.example.newsfeedapp.data

import androidx.room.Dao
import com.example.newsfeedapp.data.ArticleEntity

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Query("SELECT * FROM articles")
    suspend fun getCachedArticles(): List<ArticleEntity>

    @Query("DELETE FROM articles")
    suspend fun clearArticles()
}
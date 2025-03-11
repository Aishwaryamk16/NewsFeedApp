package com.example.newsfeedapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsfeedapp.data.ArticleDao
import com.example.newsfeedapp.data.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}
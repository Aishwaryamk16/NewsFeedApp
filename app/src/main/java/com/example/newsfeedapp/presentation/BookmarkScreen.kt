package com.example.newsfeedapp

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.newsfeedapp.presentation.viewmodal.BookmarkViewModel

@Composable
fun BookmarkScreen() {
     val viewModel: BookmarkViewModel by viewModels()
    val bookmarks by viewModel.bookmarks.collectAsState()

    LazyColumn {
        items(bookmarks) { article ->
            Text(article.title)
            Button(onClick = { viewModel.removeBookmark(article.web_url) }) {
                Text("Remove Bookmark")
            }
        }
    }
}
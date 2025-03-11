package com.example.newsfeedapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.newsfeedapp.presentation.viewmodal.NewsViewModel

@OptIn(ExperimentalMaterial3Api::class)// for using TextField in search
@Composable
fun NewsFeedScreen(viewModel: NewsViewModel) {
    val articles by viewModel.articles.collectAsState()
    val searchQuery by viewModel.query.collectAsState()
    val isOffline by viewModel.isOffline.collectAsState()

    Column {
        if (isOffline) {
            Text(
                "You're viewing cached articles",
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
            //Add search bar in UI
            TextField(
                value = searchQuery,
                onValueChange = { viewModel.searchArticles(it) },
                label = { Text("Search Articles") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        LazyColumn {
            items(articles) { article ->
                ArticleItem(article)
            }

            //Modify LazyColumn to detect when the user scrolls to the bottom
            itemsIndexed(articles) { index, article ->
                ArticleItem(article)
                if (index == articles.size - 1) {
                    viewModel.loadArticles(viewModel.query.value.toInt(), viewModel.query.toString()) // Load next page
                }
            }
        }
    }

}
@Composable
fun ArticleItem(article: Article) {
    Card(modifier = Modifier.padding(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(article.headline.main, fontWeight = FontWeight.Bold)
            Text(article.snippet)
            article.multimedia.firstOrNull()?.url?.let { imageUrl ->
                Image(
                    //add dependency for this.
                    painter = rememberAsyncImagePainter("https://static01.nyt.com/$imageUrl"),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        }
    }
    //Add bookmark
    Button(onClick = {
        //TODO
     /*   viewModel.addBookmark(
            ArticleEntity(
                article.web_url,
                article.headline.main,
                article.snippet,
                article.multimedia.firstOrNull()?.url
            )
        )*/
    }) {
        Text("Bookmark")
    }
}


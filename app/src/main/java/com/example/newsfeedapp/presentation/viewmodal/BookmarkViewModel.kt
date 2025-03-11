package com.example.newsfeedapp.presentation.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsfeedapp.data.ArticleEntity
import com.example.newsfeedapp.domain.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _bookmarks = MutableStateFlow<List<ArticleEntity>>(emptyList())
    val bookmarks: StateFlow<List<ArticleEntity>> = _bookmarks

    init {
        viewModelScope.launch {
            repository.getBookmarks().collectLatest { articles ->
                _bookmarks.value = articles
            }
        }
    }

    fun addBookmark(article: ArticleEntity) {
        viewModelScope.launch {
            repository.addBookmark(article)
        }
    }

    fun removeBookmark(url: String) {
        viewModelScope.launch {
            repository.removeBookmark(url)
        }
    }
}
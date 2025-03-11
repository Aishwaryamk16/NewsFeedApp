package com.example.newsfeedapp.presentation.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsfeedapp.Article
import com.example.newsfeedapp.domain.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    //search
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    private val _isOffline = MutableStateFlow(false)
    val isOffline: StateFlow<Boolean> = _isOffline

    private var currentPage = 1
    private var isLastPage = false

    fun loadArticles(page: Int, query: String) {
        viewModelScope.launch {
            try {
                val articles = repository.fetchArticles(page, query)
                _articles.value = articles
                _isOffline.value = false
            } catch (e: Exception) {
                _articles.value = repository.getCachedArticles()
                _isOffline.value = true
            }
        }
    }

    //search added code
 /*   fun loadArticles(query: String = "") {
        viewModelScope.launch {
            try {
                val newArticles = repository.fetchArticles(currentPage, query)
                if (newArticles.isEmpty()) {
                    isLastPage = true
                } else {
                    _articles.value = _articles.value + newArticles
                    currentPage++
                }
            } catch (e: Exception) {
                // If network fails, load cached data
                _articles.value = repository.getCachedArticles()
            }
        }
    }*/
    //Search feature
    fun searchArticles(query: String) {
        _query.value = query
        currentPage = 1
        isLastPage = false
        _articles.value = emptyList() // Clear old data
      //  loadArticles(query.toInt())
    }
}

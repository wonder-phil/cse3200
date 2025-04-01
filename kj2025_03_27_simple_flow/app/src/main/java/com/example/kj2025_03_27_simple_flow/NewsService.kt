package com.example.kj2025_03_27_simple_flow

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.kj2025_03_27_simple_flow.models.NewsArticle


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class NewsService : Service() {

        private val _newsFeed = MutableStateFlow<List<NewsArticle>>(emptyList())
        val newsFeed: StateFlow<List<NewsArticle>> = _newsFeed.asStateFlow()

        // Simulate fetching news data
        private fun fetchNewsData() {
            // Replace with your actual news data fetching logic (e.g., API call)
            val newArticles = listOf(
                NewsArticle("Article 1", "Content 1", "https://example.com/image1.jpg"),
                NewsArticle("Article 2", "Content 2", null)
            )
            _newsFeed.update { currentList -> currentList + newArticles }
        }

        override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
            // Start fetching news data in the background
            fetchNewsData()
            return START_STICKY
        }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
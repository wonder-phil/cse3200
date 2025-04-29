package com.example.kj2025_03_27_simple_flow

import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kj2025_03_27_simple_flow.ui.theme.Kj2025_03_27_simple_flowTheme

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private lateinit var newsService: NewsService
private var isBound = false

private val newsServiceConnection = object : ServiceConnection {
    override fun onServiceConnected(name: Class<*>, service: IBinder) {
        newsService = (service as NewsService.newsFeed).getService()
        isBound = true
        collectNewsFeed()
    }

    override fun onServiceDisconnected(name: Class<*>) {
        isBound = false
    }
}

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Bind to the service
    val intent = Intent(this, NewsService::class.java)
    bindService(intent, newsServiceConnection, BIND_AUTO_CREATE)
}

private fun collectNewsFeed() {
    lifecycleScope.launch {
        newsService.newsFeed.collectLatest { newsArticles ->
            // Update your UI with the news articles
            // Example: Display in a RecyclerView
            recyclerView.adapter = NewsAdapter(newsArticles)
        }
    }
}

override fun onDestroy() {
    super.onDestroy()
    if (isBound) {
        unbindService(newsServiceConnection)
        isBound = false
    }
}
}
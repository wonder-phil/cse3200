package com.example.k2025_04_28_mediaservice

import android.content.ComponentName
import android.media.session.MediaController
import android.os.Bundle
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
import androidx.media3.session.MediaBrowser
import androidx.media3.session.MediaSessionService
import com.example.k2025_04_28_mediaservice.ui.theme.K2025_04_28_mediaServiceTheme

class MainActivity : ComponentActivity() {

    private lateinit var mediaBrowser: MediaBrowser
    private lateinit var mediaController: MediaController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mediaBrowser = MediaBrowser.Builder(
            this,
            ComponentName(this, MediaSessionService::class.java)
        ).build().also {
            it.connect()
        }

        mediaBrowser.addListener(object : MediaBrowser.Listener {
            override fun onConnected(mediaController: MediaController) {
                this@MainActivity.mediaController = mediaController

                // Attach to your PlayerView
                findViewById<PlayerView>(R.id.player_view).player = mediaController

                // Optionally preload or play
                mediaController.setMediaItem(MediaItem.fromUri("https://…/video.mp4"))
                mediaController.prepare()
                mediaController.play()
            }
        })

        setContent {
            K2025_04_28_mediaServiceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    K2025_04_28_mediaServiceTheme {
        Greeting("Android")
    }
}
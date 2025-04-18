package com.example.k2025_04_17_exoplayervideo_start

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            VideoScreen()
        }
    }
}

@Composable
fun VideoScreen() {
    val context = LocalContext.current
    val videoUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4" //""http://stream.whus.org:8000/whusf"

    Column(modifier = Modifier.fillMaxSize()) {
        ExoPlayerView(context = context, videoUri = videoUrl)
    }
}

@Composable
fun ExoPlayerView(context: Context, videoUri: String) {
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(videoUri))
            prepare()
            playWhenReady = true
        }
    }

    AndroidView(factory = {
        PlayerView(it).apply {
            player = exoPlayer
            useController = true
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    })

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }
}
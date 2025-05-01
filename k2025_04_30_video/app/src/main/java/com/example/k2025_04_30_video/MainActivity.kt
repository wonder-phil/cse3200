package com.example.k2025_04_30_video

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.k2025_04_30_video.ui.theme.K2025_04_30_videoTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MainScreen()
            //VideoScreenElephant()
            //VideoScreenNasa()
            //VideoScreenTwoVideos()
        }
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
            useController = false
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

@Composable
fun TikTokVideoFeed(videoUrls: List<String>) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val listState = rememberLazyListState()
    val currentIndex = remember { derivedStateOf { listState.firstVisibleItemIndex } }

    val player = remember {
        ExoPlayer.Builder(context).build()
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    player.pause()
                }
                Lifecycle.Event.ON_RESUME -> {
                    player.play()
                }
                Lifecycle.Event.ON_DESTROY -> {
                    player.release()
                }
                else -> Unit
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    DisposableEffect(Unit) {
        onDispose { player.release() }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        itemsIndexed(videoUrls) { index, url ->
            VideoPlayerItem(
                videoUrl = url,
                isPlaying = index == currentIndex.value,
                player = player
            )
        }
    }
}


@Composable
fun VideoPlayerItem(videoUrl: String, isPlaying: Boolean, player: ExoPlayer) {
    val context = LocalContext.current
    val mediaItem = remember(videoUrl) { MediaItem.fromUri(videoUrl) }

    ExoPlayerView(context, videoUrl)

    /*
    AndroidView(
        factory = {
            PlayerView(context).apply {
                useController = true
                layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        },
        modifier = Modifier.fillMaxSize(),
        update = { playerView ->
            if (isPlaying) {
                player.setMediaItem(mediaItem)
                player.prepare()
                player.playWhenReady = true
                playerView.player = player
            } else {
                // Important: Unbind the player from this view to avoid surface reuse bugs
                if (playerView.player == player) {
                    playerView.player = null
                }
            }
        }


    )

     */

}


@Composable
fun MainScreen() {
    val sampleUrls = listOf(
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
        //"https://images-assets.nasa.gov/video/JPL-20190326-TECHf-0001-Mars%20Helicopter%20VF/JPL-20190326-TECHf-0001-Mars%20Helicopter%20VF~orig.mp4",
        //"http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"

    )

    TikTokVideoFeed(sampleUrls)
}


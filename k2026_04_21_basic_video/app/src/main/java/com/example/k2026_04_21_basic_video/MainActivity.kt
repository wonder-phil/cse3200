package com.example.k2026_04_21_basic_video

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.k2026_04_21_basic_video.ui.theme.K2026_04_21_basic_videoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            K2026_04_21_basic_videoTheme {
                VideoScreenTwoVideos()
                //VideoScreenNasa()
            }
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
fun VideoScreenNasa() {
    val context = LocalContext.current
    val videoUrl = "https://images-assets.nasa.gov/video/JPL-20190326-TECHf-0001-Mars%20Helicopter%20VF/JPL-20190326-TECHf-0001-Mars%20Helicopter%20VF~orig.mp4" //""http://stream.whus.org:8000/whusf"

    Column(modifier = Modifier.fillMaxSize()) {
        ExoPlayerView(context = context, videoUri = videoUrl)
    }
}


@Composable
fun VideoScreenTwoVideos() {
    val context = LocalContext.current
    val videoUrlNasa = "https://images-assets.nasa.gov/video/JPL-20190326-TECHf-0001-Mars%20Helicopter%20VF/JPL-20190326-TECHf-0001-Mars%20Helicopter%20VF~orig.mp4" //""http://stream.whus.org:8000/whusf"
    //val videoUrlElephant = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4" //""http://stream.whus.org:8000/whusf"

    var videoUrlElephant = "https://images-assets.nasa.gov/video/JPL-20190326-TECHf-0001-Mars%20Helicopter%20VF/JPL-20190326-TECHf-0001-Mars%20Helicopter%20VF~orig.mp4"



    Column(modifier = Modifier.fillMaxSize()) {
        ExoPlayerView(context = context, videoUri = videoUrlNasa)
        ExoPlayerView(context = context, videoUri = videoUrlElephant)
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
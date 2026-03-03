package com.example.k2026_03_02_basic_radio

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.k2026_03_02_basic_radio.ui.theme.K2026_03_02_basic_radioTheme

class MainActivity : ComponentActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private var isRadioPrepared: Boolean = false

    private val uconn_radio = "http://stream.whus.org:8000/whusfm"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setUpRadio(uconn_radio)
        setContent {
            K2026_03_02_basic_radioTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RadioOnOff(
                        isRadioPrepared = isRadioPrepared,
                        mediaPlayer = mediaPlayer,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun setUpRadio(myUrl: String) {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setOnPreparedListener { isRadioPrepared = true }
            setOnErrorListener { _, what, extra ->
                Log.e("PGB", "Error $what and $extra")
                true
            }
            setDataSource(myUrl)
            prepareAsync()
        }
    }
}

@Composable
fun RadioOnOff(isRadioPrepared: Boolean, mediaPlayer: MediaPlayer?, modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()) {
        Button(onClick = { if (isRadioPrepared)  { mediaPlayer?.start() }  }) {
            Text("Radio on")
        }
        Button(onClick = {}) {
            Text("Radio off")
        }
        Text(
            text = "Hello",
            modifier = modifier
        )
    }
}

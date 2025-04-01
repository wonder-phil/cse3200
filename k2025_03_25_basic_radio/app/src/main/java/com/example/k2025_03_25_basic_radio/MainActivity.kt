package com.example.k2025_03_25_basic_radio

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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.k2025_03_25_basic_radio.ui.theme.K2025_03_25_basic_radioTheme

val url_UConn = "http://stream.whus.org:8000/whusfm"
val url_UK = "https://radio.canstream.co.uk:8083/live.mp3"

class MainActivity : ComponentActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private var radioOn: Boolean = false

    private var radioIsSetup: Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setUpRadio(url_UConn)
        setContent {
            K2025_03_25_basic_radioTheme {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Radio!!",
                        )
                    Button(onClick = {
                        Log.i("PGB", "Radio start");
                        if (radioIsSetup) mediaPlayer?.start()
                        }) {
                            Text(text = "Start Radio")
                        }
                    Button(onClick = {
                        Log.i("PGB", "Radio stop");
                        mediaPlayer?.stop();
                        mediaPlayer?.release()
                        radioIsSetup = false
                        }) {
                            Text(text = "Stop Radio")
                        }
                    Button(onClick = {
                        Log.i("PGB", "Radio station switch");
                            if (radioIsSetup) {
                                mediaPlayer?.stop();
                                mediaPlayer?.release()
                                mediaPlayer = null
                                setUpRadio(url_UK)
                            }
                    }) {
                        Text(text = "Switch Radio Station")
                    }
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
            setDataSource(myUrl)
            prepareAsync()
        }
    }
}


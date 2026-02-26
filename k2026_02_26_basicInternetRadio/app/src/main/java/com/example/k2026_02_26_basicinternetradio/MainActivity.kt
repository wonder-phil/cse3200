package com.example.k2026_02_26_basicinternetradio

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
import androidx.compose.ui.tooling.preview.Preview
import com.example.k2026_02_26_basicinternetradio.ui.theme.K2026_02_26_basicInternetRadioTheme

class MainActivity : ComponentActivity() {

    private val url_UConn = "http://stream.whus.org:8000/whusfm"
    private val url_UK = "https://radio.canstream.co.uk:8083/live.mp3"

    private var mediaPlayer: MediaPlayer? = null
    private var radioIsPrepared: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setUpRadio(url_UK)

        setContent {
            K2026_02_26_basicInternetRadioTheme {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text("Radio!!")

                    Button(onClick = {
                        Log.i("PGB", "Radio start clicked. prepared=$radioIsPrepared")
                        if (radioIsPrepared) {
                            mediaPlayer?.start()
                        } else {
                            Log.i("PGB", "Not prepared yet (still buffering).")
                        }
                    }) { Text("Start Radio") }

                    Button(onClick = {
                        Log.i("PGB", "Radio stop")
                        // stop() is only valid if prepared/started; otherwise it can throw
                        runCatching { mediaPlayer?.stop() }
                        mediaPlayer?.reset()
                        mediaPlayer?.release()
                        mediaPlayer = null
                        radioIsPrepared = false
                    }) { Text("Stop Radio") }

                    Button(onClick = {
                        Log.i("PGB", "Switch station")
                        // clean up old
                        runCatching { mediaPlayer?.stop() }
                        mediaPlayer?.reset()
                        mediaPlayer?.release()
                        mediaPlayer = null
                        radioIsPrepared = false

                        // switch to UK for testing (HTTPS is usually easier)
                        setUpRadio(url_UConn)
                    }) { Text("Switch Radio Station") }
                }
            }
        }
    }

    private fun setUpRadio(myUrl: String) {
        Log.i("PGB", "Setting up radio: $myUrl")

        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setOnPreparedListener {
                radioIsPrepared = true
                Log.i("PGB", "Prepared OK")
            }
            setOnErrorListener { _, what, extra ->
                Log.e("PGB", "MediaPlayer error what=$what extra=$extra")
                true
            }

            setDataSource(myUrl)
            prepareAsync()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        runCatching { mediaPlayer?.stop() }
        mediaPlayer?.release()
        mediaPlayer = null
        radioIsPrepared = false
    }
}




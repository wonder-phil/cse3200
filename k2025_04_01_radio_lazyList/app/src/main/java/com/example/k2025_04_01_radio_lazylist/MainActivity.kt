package com.example.k2025_04_01_radio_lazylist

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.k2025_04_01_radio_lazylist.models.RadioStationsManager
import com.example.k2025_04_01_radio_lazylist.ui.theme.K2025_04_01_radio_lazyListTheme
import com.example.k2025_04_01_radio_lazylist.ui.theme.LightBlue


class MainActivity : ComponentActivity() {

    private var mediaPlayer: MediaPlayer? = null
    private var radioOn: Boolean = false

    private val radioStationsManager = RadioStationsManager()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            K2025_04_01_radio_lazyListTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("Cool radio stations")
                            },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = LightBlue
                            )
                        )
                    }) { innerPadding ->
                    ScrollStateList(radioStationsManager, innerPadding)
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

@Composable
fun ScrollStateList(radioStationsManager: RadioStationsManager, innerPaddingValues: PaddingValues) {
    // Remember the scroll state
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        items(radioStationsManager.getNumberOfRadioStations(), key = { it }) { index ->
            Text(
                text = "Item #$index : ${radioStationsManager.getStation(index).name}",
                modifier = Modifier.padding(16.dp).clickable {
                    println("Clicked #$index")
                }
                ,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}


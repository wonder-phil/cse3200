package com.example.k2026_03_09_radio

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.k2026_03_09_radio.ViewModels.RadioStation
import com.example.k2026_03_09_radio.ViewModels.RadioStationViewModel

import com.example.k2026_03_09_radio.ui.theme.K2026_03_09_radioTheme


class MainActivity : ComponentActivity() {

    private val url_UK = "https://radio.canstream.co.uk:8083/live.mp3"

    private var mediaPlayer: MediaPlayer? = null
    private var radioIsPrepared: Boolean = false
    private var radioIsPlaying: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            K2026_03_09_radioTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GetDataFunction(
                        { s -> playRadioStation(s) },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun setUpRadio(myUrl: String)  {
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
                it?.start()
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
        StopAndDestroyRadio()
    }

    fun StopAndDestroyRadio() {
        runCatching { mediaPlayer?.stop() }
        mediaPlayer?.release()
        mediaPlayer = null

        radioIsPrepared = false
        radioIsPlaying = false
    }

    fun playRadioStation(radioUrl: String?) {
        if (null == radioUrl) {
            Log.i("PGB", "No radio station")
            return
        }
        if (radioIsPlaying) {
            StopAndDestroyRadio()
        }
        setUpRadio(radioUrl)
        radioIsPlaying = true
    }
}

@Composable
fun GetDataFunction(playRadioStation: (String?) -> Unit, modifier: Modifier = Modifier) {

    val radioStations by RadioStationViewModel().radioStationsFlow.collectAsState(initial =  emptyList())

    Log.i("PGB", "radio stations ${radioStations}")
    Column( verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Radio Lab",
            fontSize = 32.sp,
            modifier = modifier
        )
        lazyRadioStatonColumn(radioStations.toList(), playRadioStation)
    }
}

@Composable
fun lazyRadioStatonColumn(radioStations: List<RadioStation>, playRadioStation: (String?) -> Unit ) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(radioStations) { station ->
            RadioStationCard(
                station = station,
                playRadioStation = { url -> playRadioStation(url) }
            )
        }

        /*
        items(
            items = radioStations,
            key = { it.stationuuid!! }   // stable key = smooth scrolling
        ) { station ->

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                onClick = { Log.i("PGB","Card clicked ${station.stationuuid} and ${station.url_resolved}");
                    playRadioStation(station?.url) }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {

                    Row(horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(6.dp)) {
                        AsyncImage(
                            model = station.favicon,
                            contentDescription = station.name,
                            modifier = Modifier.size(60.dp)
                        )
                        Text(text = "${station.name.toString().take(20)}. ${station.country}")
                    }
                    Text(text = "${station.country}, ${station.state} ")
                    Log.i("PGB", "URL: ${station.url}")
                }
            }
        }

         */
    }
}

@Composable
fun RadioStationCard(
    station: RadioStation,
    playRadioStation: (String?) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        onClick = { playRadioStation(station.url) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = station.favicon?.takeIf { it.isNotBlank() },
                contentDescription = station.name,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = station.name ?: "Unknown Station",
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = listOfNotNull(station.country, station.state)
                        .filter { it.isNotBlank() }
                        .joinToString(", ")
                        .ifBlank { "Location unknown" },
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            FilledTonalIconButton(
                onClick = { playRadioStation(station.url) }
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play station"
                )
            }
        }
    }
}


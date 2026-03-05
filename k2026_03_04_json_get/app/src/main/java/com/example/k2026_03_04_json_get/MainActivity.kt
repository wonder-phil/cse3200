package com.example.k2026_03_04_json_get

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.k2026_03_04_json_get.ui.theme.K2026_03_04_json_getTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            K2026_03_04_json_getTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GetDataFunction(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GetDataFunction(name: String, modifier: Modifier = Modifier) {

    val radioStations by RadioStationViewModel().radioStationsFlow.collectAsState(initial =  emptyList())

    Log.i("PGB", "radio stations ${radioStations}")
    Column( verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()) {
        Button(onClick = { Log.i("PGB", "radio stations ${radioStations}") }) {
            Text("Get data")
        }
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
}

package com.example.k2025_04_07_boundservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import com.example.k2025_04_07_boundservice.services.RandomIntService
import com.example.k2025_04_07_boundservice.ui.theme.K2025_04_07_boundServiceTheme

class MainActivity : ComponentActivity() {

    private var isConnected: Boolean = false
    private lateinit var randomIntService: RandomIntService

    private val connection = object: ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as RandomIntService.LocalBinder
            randomIntService = binder.getService()
            isConnected = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isConnected = false
        }

    }

    override fun onStart() {
        super.onStart()
        Intent(this, RandomIntService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if (isConnected) {
            unbindService(connection)
            isConnected = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var myInt: Int = 0
        enableEdgeToEdge()

        setContent {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxSize(0.9f)) {
                    Button(onClick={ Log.i("PGB", "myInt: $myInt"); myInt = randomIntService.generateRandomInt() }) {
                        Text("Get random number")
                    }
                    Text("Value: ${myInt}")
                    Greeting({ randomIntService.generateRandomInt() }, modifier = Modifier)
                }
            }
        }
    }


@Composable
fun Greeting(captureFunction: () -> Int, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly) {
        var myInt = remember { mutableStateOf(0) }
        Text(
            text = "Number: ${myInt.value} !",
            modifier = modifier
        )
        Button(onClick = { myInt.value = captureFunction() } ) {
            Text("Get value")
        }
    }

}

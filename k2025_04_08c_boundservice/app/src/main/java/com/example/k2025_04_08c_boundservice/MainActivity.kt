package com.example.k2025_04_08c_boundservice

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
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.ui.unit.sp
import com.example.k2025_04_08c_boundservice.service.RandomIntService
import com.example.k2025_04_08c_boundservice.ui.theme.K2025_04_08c_boundserviceTheme

class MainActivity : ComponentActivity() {

    private var isConnected = false
    private lateinit var randomIntService: RandomIntService

    private val connector = object: ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as RandomIntService.RandomIntServiceBinder
            randomIntService = binder.getRandomIntService()

            isConnected = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isConnected = false
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, RandomIntService::class.java).also { intent->
            bindService(intent, connector, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var myInt = 0
        enableEdgeToEdge()
        setContent {
            Column(verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(0.9f)) {
                Button(onClick = { myInt = randomIntService.getRandomNumber();
                                    Log.i("PGB", "$myInt") } ) {
                    Text("Get random int", fontSize = 32.sp)
                }
                Text("myInt $myInt", fontSize = 32.sp)
                ShowMyInt( { randomIntService.getRandomNumber() } )
            }
        }
    }
}

@Composable
fun ShowMyInt(myReference2Randomness: () -> Int, modifier: Modifier = Modifier) {
    var myInt = remember { mutableStateOf(0) }

    Text(
        text = "Hello ${myInt.value}!",
        fontSize = 32.sp,
        modifier = modifier
    )
    Button(onClick = { myInt.value = myReference2Randomness() } ) {
        Text("RInt Screen", fontSize = 32.sp)
    }
}

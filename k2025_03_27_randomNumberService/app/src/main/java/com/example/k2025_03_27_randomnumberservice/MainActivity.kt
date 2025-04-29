package com.example.k2025_03_27_randomnumberservice

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import com.example.k2025_03_27_randomnumberservice.service.LocalService
import com.example.k2025_03_27_randomnumberservice.ui.theme.K2025_03_27_randomNumberServiceTheme

class MainActivity : ComponentActivity() {

    private var isBound = false
    private lateinit var localService: LocalService
    private var connection: ServiceConnection

    init {
        connection = object : ServiceConnection {
            override fun onServiceConnected(p0: ComponentName?, service: IBinder?) {
                val binder = service as LocalService.LocalBinder
                localService = binder.getService()
                Log.i("PGB", "INIT")
                isBound = true
            }

            override fun onServiceDisconnected(p0: ComponentName?) {
                isBound = false
            }
        }
    }

    override fun onStop() {
        super.onStop()
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
    }

    private fun useServiceSafely() : Int {
        var result = -1
        if (isBound) {
            // Now it's safe to use localService
            result = localService.generateRandomNumber()
        }
        return result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            K2025_03_27_randomNumberServiceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        localService,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, LocalService::class.java).also {
            intent -> bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }
}

@Composable
fun Greeting(name: String, localService: LocalService, modifier: Modifier = Modifier) {
    var myCount = remember { mutableStateOf(0) }
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    Button(onClick={ myCount.value = localService.generateRandomNumber() }) {
        Text("Count $myCount")
    }
}

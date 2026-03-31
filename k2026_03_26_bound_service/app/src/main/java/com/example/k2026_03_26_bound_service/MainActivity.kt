package com.example.k2026_03_26_bound_service

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
import com.example.k2026_03_26_bound_service.services.LocalService
import com.example.k2026_03_26_bound_service.ui.theme.K2026_03_26_bound_serviceTheme

class MainActivity : ComponentActivity() {

    private var isBound = false
    private var localService: LocalService? = null
    private lateinit var connection: ServiceConnection


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {

                if (service is LocalService.LocalBinder) {
                    localService = service.getService()
                    isBound = true
                    Log.i("PGB", "Service successfully connected")
                }
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                localService = null
                isBound = false
            }
        }

        enableEdgeToEdge()
        setContent {
            K2026_03_26_bound_serviceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    ServiceConsole(
                        { localService?.generateRandomNumber() } as () -> Int?,
                        isBound = isBound,
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Intent(this, LocalService::class.java).also { intent ->
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if (isBound) {
            unbindService(connection)
            isBound = false
        }
    }
}

fun useServiceSafely(localService: () -> Int?, isBound: Boolean, ) : Int? {
    var result: Int? = -1
    if (isBound) {

        //if (localService == null)  Log.i("PGB", "localService is null")
        result = localService?.invoke() as Int?
        Log.i("PGB", "Getting random number<: $result")
    }
    return result
}


@Composable
fun ServiceConsole(localService: () -> Int?, isBound: Boolean, modifier: Modifier = Modifier) {
    var dataFromService = remember { mutableStateOf(0) }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()) {
        Text(
            text = "Returned value is ${dataFromService.value}",
            modifier = modifier
        )
        Button(onClick={ dataFromService.value = useServiceSafely(localService, true ) ?: -1   }
        ) {
            Text("Get data from service")
        }
    }
}

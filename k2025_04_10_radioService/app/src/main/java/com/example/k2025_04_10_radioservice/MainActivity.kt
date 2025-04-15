package com.example.k2025_04_10_radioservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.os.RemoteException
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
import com.example.k2025_04_10_radioservice.models.RadioStates
import com.example.k2025_04_10_radioservice.services.RadioService

class MainActivity : ComponentActivity() {

    private var mService: Messenger? = null
    private var bound: Boolean = false


    private val mConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            //We are communicating with the
            // service using a Messenger, so here we get a client-side
            // representation of that from the raw IBinder object.
            mService = Messenger(service)
            bound = true
        }

        override fun onServiceDisconnected(className: ComponentName) {
            mService = null
            bound = false
        }
    }

    fun setUpMediaPlayer() {
        val msg: Message = Message.obtain(null, RadioStates.SETUP_RADIO.stateInteger, 0, 0)
        try {
            mService?.send(msg)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun runMediaPlayer() {
        val msg: Message = Message.obtain(null, RadioStates.START.stateInteger, 0, 0)
        try {
            mService?.send(msg)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    fun stopNDestroyMediaPlayer() {
        val msg1: Message = Message.obtain(null, RadioStates.STOP.stateInteger, 0, 0)
        try {
            mService?.send(msg1)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
        val msg2: Message = Message.obtain(null, RadioStates.DESTROY.stateInteger, 0, 0)
        try {
            mService?.send(msg2)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }
    }

    override fun onStart() {
        super.onStart()
        // Bind to the service.
        Intent(this, RadioService::class.java).also { intent ->
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        // Unbind from the service.
        if (bound) {
            unbindService(mConnection)
            bound = false
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

                Button(onClick={ setUpMediaPlayer() }) {
                    Text("Start media player")
                }

                Button(onClick = { runMediaPlayer() } ) {
                    Text("Run media player")
                }

                Button(onClick = { stopNDestroyMediaPlayer() } ) {
                    Text("Stop and destroy media player")
                }
            }
        }
    }
}


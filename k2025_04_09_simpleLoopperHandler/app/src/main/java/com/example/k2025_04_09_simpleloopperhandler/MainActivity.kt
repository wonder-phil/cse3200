package com.example.k2025_04_09_simpleloopperhandler

import android.os.Bundle
import android.os.Message
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
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    private lateinit var simpleLooperHandler: SimpleLooperHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        simpleLooperHandler = SimpleLooperHandler()
        simpleLooperHandler.start()

        var message: Message = Message()
        message.arg1 = 9
        message.arg2 = -55

        setContent {
           Column(verticalArrangement = Arrangement.SpaceEvenly,
               horizontalAlignment = Alignment.CenterHorizontally,
               modifier = Modifier.fillMaxSize(0.9f)
            ) {
               Button(onClick = {
                   simpleLooperHandler.mHandler.sendMessage(message)
                   message = Message()
                   message.arg1 = 999
                   message.arg2 = -5566
               }) { Text("Send Message", fontSize = 32.sp) }
           }
        }
    }
}


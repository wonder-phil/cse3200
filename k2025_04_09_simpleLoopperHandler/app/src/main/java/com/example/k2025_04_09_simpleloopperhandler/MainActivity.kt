package com.example.k2025_04_09_simpleloopperhandler

import android.os.Bundle
import android.os.Message
import android.widget.EditText
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.k2025_04_09_simpleloopperhandler.ui.theme.K2025_04_09_simpleLoopperHandlerTheme

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
        //message.obj =

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
               }) {
                   Text("Send Message", fontSize = 32.sp)
               }

           }
        }
    }
}


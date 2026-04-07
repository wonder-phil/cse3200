package com.example.k2026_04_6_looperhandler

import android.os.Bundle
import android.os.Message
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
import androidx.compose.ui.unit.sp
import com.example.k2026_04_6_looperhandler.ui.theme.K2026_04_6_looperHandlerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {  UpdateText("Hello!") }
    }
}


@Composable
fun UpdateText(textToShow: String, modifier: Modifier = Modifier) {
    val myText = remember { mutableStateOf(textToShow) }

    var simpleLooperHandler = SimpleLooperHandler()
    simpleLooperHandler = SimpleLooperHandler()
    simpleLooperHandler.start()

    var message: Message = Message()
    message.arg1 = 9
    message.arg2 = -55
    message.obj = myText

    Column(verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(0.9f)
    ) {
        Button(onClick = {
            simpleLooperHandler.mHandler.sendMessage(message)
            message = Message()
        }) { Text(textToShow, fontSize = 32.sp) }
        Text(myText.value, fontSize = 32.sp)
    }
}
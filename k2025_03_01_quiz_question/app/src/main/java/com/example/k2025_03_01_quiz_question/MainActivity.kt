package com.example.k2025_03_01_quiz_question

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.k2025_03_01_quiz_question.ui.theme.K2025_03_01_quiz_questionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            K2025_03_01_quiz_questionTheme {

                    myScreen(
                        "Android"
                    )
                }
            }
        }
    }


@Composable
fun myScreen(myString: String) {
    Column(verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(0.7f)) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick ={}) {
                Text("Left button")
            }
            Button(onClick ={}) {
                Text("Right button")
            }
        }
        Text(text = "Thank you $myString", fontSize = 32.sp)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    K2025_03_01_quiz_questionTheme {
        Greeting("Android")
    }
}
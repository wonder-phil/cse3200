package com.example.k2025_02_04a

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.k2025_02_04a.ui.theme.K2025_02_04aTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            K2025_02_04aTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun myButton(myText: String) {
    var count = rememberSaveable() {
        mutableStateOf<Int>(0)
    }
    Button(onClick = {
        count.value++
    }) {
        Column() {
            Text(text = "Value: ${count.value}")
            Text(text = "Press button")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Hello $name!",
            fontSize = 44.sp,
            modifier = modifier
        )
        Text(
            text = "Good Day",
            fontSize = 44.sp,
            modifier = modifier
        )
        myButton("Good day! Press")

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    K2025_02_04aTheme {
        Greeting("Android")
    }
}
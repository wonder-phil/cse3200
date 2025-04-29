package com.example.k2025_02_19_coroutines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.k2025_02_19_coroutines.ui.theme.K2025_02_19_coroutinesTheme

cimport android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    var text by remember { mutableStateOf("Hello, Jetpack Compose!") }
    // Remember a CoroutineScope tied to the composable's lifecycle
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Coroutine Example") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(text = text, style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                // Launch a coroutine when the button is clicked
                scope.launch {
                    text = "Loading..."
                    delay(2000L) // Simulate a long-running task
                    text = "Coroutine Complete!"
                }
            }) {
                Text("Start Coroutine")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    K2025_02_19_coroutinesTheme {
        Greeting("Android")
    }
}
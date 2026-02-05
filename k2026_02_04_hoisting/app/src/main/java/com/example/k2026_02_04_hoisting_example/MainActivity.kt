package com.example.k2026_02_04_hoisting_example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.magnifier
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.k2026_02_04_hoisting_example.ui.theme.K2026_02_04_hoisting_exampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            K2026_02_04_hoisting_exampleTheme {
                CounterScreen(modifier = Modifier.padding(all = 40.dp))
            }
        }
    }
}

@Composable
fun Counter(count: Int, onIncrement: () -> Unit, modifier: Modifier) {
    Column(modifier = Modifier.padding(vertical = 30.dp)) {
        Text(text = "Count: $count")
        Button(onClick = { onIncrement() }) {
            Text(text = "Increment")
        }
    }
}

@Composable
fun CounterScreen(modifier: Modifier) {
    var count = remember { mutableStateOf(0) }
    Column( modifier = modifier.fillMaxSize(0.9f),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center) {
        Counter(count = count.value, onIncrement = { count.value++ }, modifier)
    }
}

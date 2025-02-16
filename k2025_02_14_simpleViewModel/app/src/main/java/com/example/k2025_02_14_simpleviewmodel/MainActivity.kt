package com.example.k2025_02_14_simpleviewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.k2025_02_14_simpleviewmodel.ui.theme.K2025_02_14_simpleViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            K2025_02_14_simpleViewModelTheme {
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
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column() {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        MyScreen()
    }
}


@Composable
fun MyScreen() {
    val myViewModel: SimpleViewModel = SimpleViewModel()

    // Display the text from the ViewModel
    Text(text = myViewModel.myText,
        fontSize = 32.sp)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    K2025_02_14_simpleViewModelTheme {
        Greeting("Android")
    }
}
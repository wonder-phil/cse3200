package com.example.k2025_02_14_simpleviewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.k2025_02_14_simpleviewmodel.ui.theme.K2025_02_14_simpleViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val mySimpleViewModel = ViewModelProvider(this)[SimpleViewModel::class.java]

        setContent {
            K2025_02_14_simpleViewModelTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        mySimpleViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, myViewModel: SimpleViewModel, modifier: Modifier) {
    Column() {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        MyScreen(myViewModel)
    }
}


@Composable
fun MyScreen(myViewModel: SimpleViewModel) {

    Column() {
        Text(text = myViewModel.myText + " value: ${SimpleViewModel.getMyIndex()}",
            fontSize = 32.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { SimpleViewModel.incrementIndex() }) {
            Text("Increment",
                fontSize = 32.sp)
        }
    }
}

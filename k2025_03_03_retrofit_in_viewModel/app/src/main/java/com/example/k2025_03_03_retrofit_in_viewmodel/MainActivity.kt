package com.example.k2025_03_03_retrofit_in_viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import androidx.lifecycle.ViewModelProvider
import com.example.k2025_03_03_retrofit_in_viewmodel.ViewModels.BitcoinViewModel
import com.example.k2025_03_03_retrofit_in_viewmodel.ui.theme.K2025_03_03_retrofit_in_viewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val bitcoinViewModel = ViewModelProvider(this)[BitcoinViewModel::class.java]
        setContent {
            K2025_03_03_retrofit_in_viewModelTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        { bitcoinViewModel.getOneBitcoin() },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, fcn: ()->Unit, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize(0.8f)) {
        Spacer(Modifier.height(30.dp))
        Button(onClick={ fcn() }) {
            Text("Select", fontSize = 32.sp)
        }
    }
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

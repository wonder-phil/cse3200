package com.example.k2025_03_05_viewcontrollerlivedata

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.k2025_03_05_viewcontrollerlivedata.models.BitcoinBlock
import com.example.k2025_03_05_viewcontrollerlivedata.models.BitcoinBlockViewModel
import com.example.k2025_03_05_viewcontrollerlivedata.ui.theme.K2025_03_05_viewControllerLiveDataTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        val bitcoinBlock = ViewModelProvider(this)[BitcoinBlockViewModel::class.java]

        setContent {
            K2025_03_05_viewControllerLiveDataTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize(0.8f).padding(innerPadding)) {
                        Button(onClick = { bitcoinBlock.getOneBitcoin() } ) { //
                            Text("Select", fontSize = 32.sp)
                        }
                    }
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
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    K2025_03_05_viewControllerLiveDataTheme {
        Greeting("Android")
    }
}
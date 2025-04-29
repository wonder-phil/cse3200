package com.example.k2025_03_11a_bitcoin_explorer

import android.os.Bundle
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.k2025_03_11a_bitcoin_explorer.models.BitcoinViewModel
import com.example.k2025_03_11a_bitcoin_explorer.ui.theme.K2025_03_11a_bitcoin_explorerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val bitcoinViewModel = ViewModelProvider(this)[BitcoinViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            K2025_03_11a_bitcoin_explorerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        bitcoinViewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, btcViewMoodel: BitcoinViewModel,modifier: Modifier = Modifier) {

    val btcHash by btcViewMoodel.bitcoinHash.observeAsState()

    Column(verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize(0.9f)) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        Button(onClick = { btcViewMoodel.getOneBitcoin() } ) {
            Text(text = "Get a bitcoin", fontSize = 32.sp)
        }
        Text(text =  btcHash.toString() , fontSize = 32.sp)
    }


}

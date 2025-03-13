package com.example.k2025_03_11_block_explorer

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
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.k2025_03_11_block_explorer.models.BlockViewModel
import com.example.k2025_03_11_block_explorer.ui.theme.K2025_03_11_block_explorerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val blockViewModel = ViewModelProvider(this)[BlockViewModel::class.java]

        val myTextObserver by blockViewModel.btcHash.observeAsState()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            K2025_03_11_block_explorerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   Column(verticalArrangement = Arrangement.SpaceEvenly,
                       horizontalAlignment = Alignment.CenterHorizontally,
                       modifier = Modifier.fillMaxSize(0.9f).padding(innerPadding)) {
                       Text("Hello", fontSize = 32.sp )
                       Button(onClick={ blockViewModel.getOneBitcoin() } ) {
                           Text("Get a bitcoin", fontSize = 32.sp)
                       }
                       Text( text= myTextObserver.toString() )
                   }
                }
            }
        }
    }
}

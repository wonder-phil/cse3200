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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.k2025_03_11_block_explorer.models.BlockViewModel
import com.example.k2025_03_11_block_explorer.ui.theme.K2025_03_11_block_explorerTheme

class MainActivity : ComponentActivity() {

    fun buttonText(blockTip: Boolean) : String {
        if (blockTip) {
            return "Get most recent BTC"
        } else {
            return "Go back one BTC"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        val blockViewModel = ViewModelProvider(this)[BlockViewModel::class.java]

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var blockTip = remember { mutableStateOf(true) }

            val myTextObserver by blockViewModel.currentBlockHash.observeAsState()

            K2025_03_11_block_explorerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   Column(verticalArrangement = Arrangement.SpaceEvenly,
                       horizontalAlignment = Alignment.CenterHorizontally,
                       modifier = Modifier.fillMaxSize(0.9f).padding(innerPadding)) {
                       Text("Block explorer", fontSize = 32.sp )
                       Button(onClick={ if (blockTip.value) blockViewModel.getBitcoinChainTip() else blockViewModel.getPreviousBlock();
                                        blockTip.value = false } ) {
                           Text(buttonText(blockTip.value), fontSize = 32.sp)
                       }
                       Text( text= myTextObserver.toString(),
                           fontSize = 32.sp)
                   }
                }
            }
        }
    }
}

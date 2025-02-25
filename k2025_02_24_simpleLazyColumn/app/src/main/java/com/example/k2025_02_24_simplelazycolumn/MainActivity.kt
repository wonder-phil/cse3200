package com.example.k2025_02_24_simplelazycolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.k2025_02_24_simplelazycolumn.ui.theme.K2025_02_24_simpleLazyColumnTheme

class MainActivity : ComponentActivity() {
    val padding = 16.dp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            K2025_02_24_simpleLazyColumnTheme {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize(0.9f)) {
                    SampleLazyColumn(modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun SampleLazyColumn(modifier: Modifier) {
    val itemsList = (1..50).toList()
    LazyColumn() {
        items(itemsList) { item ->
            Text(text = "Item: $item")
        }
    }
}

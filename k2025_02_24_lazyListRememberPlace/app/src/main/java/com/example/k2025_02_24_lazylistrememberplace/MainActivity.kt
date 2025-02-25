package com.example.k2025_02_24_lazylistrememberplace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.k2025_02_24_lazylistrememberplace.ui.theme.K2025_02_24_lazyListRememberPlaceTheme
import com.example.k2025_02_24_lazylistrememberplace.ui.theme.LightBlue
import com.example.k2025_02_24_lazylistrememberplace.ui.theme.Pink40

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            K2025_02_24_lazyListRememberPlaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("Top App Bar")
                            },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = LightBlue
                            )
                        )
                    }) { innerPadding ->
                    ScrollStateList(innerPadding)
                }
            }
        }
    }
}

@Composable
fun ScrollStateList(innerPadding: PaddingValues) {
    // Remember the scroll state
    val listState = rememberLazyListState()

    LazyColumn(state = listState) {
        items(100, key = { it }) { index ->
            Text(
                text = "Item #$index",
                modifier = Modifier.padding(16.dp).clickable {
                    println("Clicked #$index")
                }
                ,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

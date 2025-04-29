package com.example.k2025_04_22_one_met_image

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
//import androidx.compose.foundation.layout.ColumnScopeInstance.align
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import com.example.k2025_04_22_one_met_image.ui.theme.K2025_04_22_One_MET_imageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            K2025_04_22_One_MET_imageTheme {
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment =  Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize(0.5f)) {
                    OneImage("https://images.metmuseum.org/CRDImages/ep/web-large/DT1567.jpg",
                        modifier = Modifier.align(Alignment.CenterHorizontally))
                }

            }
        }
    }
}

@Composable
fun OneImage(url: String, modifier: Modifier = Modifier) {
    val painter = rememberAsyncImagePainter(url)

    Image(
        painter = painter,
        contentDescription = "Image from MET",
        modifier = Modifier.clickable(onClick = { Log.i("PGB", "Dont touch the paintings!") }),
        contentScale = ContentScale.Crop
    )
}

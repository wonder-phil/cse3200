package com.example.k2025_02_19_modelview

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.k2025_02_19_modelview.ui.theme.K2025_02_19_modelViewTheme

class MainActivity : ComponentActivity() {

    private lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]
        var myName: String = "Android: "
        var myNumber: Int = 0

        setContent {
            MyScreen(viewModel = myViewModel, modifier = Modifier)
        }
    }
}


@Composable
fun MyScreen(viewModel: MyViewModel, modifier: Modifier) {
    val text by viewModel.text.observeAsState()

    Column( horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()) {
        Text(text = text ?: "Loading...", fontSize = 38.sp)

        Button(onClick = { viewModel.updateText("New Text") }) {
            Text("Update Text", fontSize = 26.sp)
        }
    }
}

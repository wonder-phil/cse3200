package com.example.k2025_02_20a_viewmodel

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
import com.example.k2025_02_20a_viewmodel.ui.theme.K2025_02_20a_viewModelTheme
import com.example.k2025_02_20a_viewmodel.viewModels.MyViewModel

class MainActivity : ComponentActivity() {

    lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]

        setContent {
            K2025_02_20a_viewModelTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        myViewModel,
                        "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(myViewModel: MyViewModel, name: String, modifier: Modifier = Modifier) {

    val myTextObserver by myViewModel.myText.observeAsState()
    val myIntObserver by myViewModel.myInt.observeAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )

        Text(text = myTextObserver.toString(), fontSize = 32.sp)
        Button(onClick ={ myViewModel.updateText("Good day")}) {
            Text(text = "Update myText")
        }

        Text(text = "${myIntObserver}", fontSize = 32.sp)
        Button(onClick = { myViewModel.incrementMyInt() } ) {
            Text(text = "Increment", fontSize = 32.sp)
        }
    }
}

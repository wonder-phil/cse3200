package com.example.k2025_02_17_livedatainviewmodel

import MyViewModelLiveData
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.k2025_02_17_livedatainviewmodel.ui.theme.K2025_02_17_liveDataInViewModelTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val myViewModelLiveData = ViewModelProvider(this)[MyViewModelLiveData::class.java]
        var nameTextView: Int

        var countObserver = Observer<Int> { newValue ->
            nameTextView = newValue
        }

        myViewModelLiveData.myCount.observe(this, countObserver)

        setContent {
            K2025_02_17_liveDataInViewModelTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column() {
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                        Button(onClick = { countObserver.onChanged( -9) } ) {
                            Text(text = "Change value")
                        }
                        MyScreen(myViewModelLiveData)
                    }
                }
            }
        }
    }
}

@Composable
fun myCountDisplay(myLocalCount: Int) {
    Text(text = "Count: $myLocalCount")
}

@Composable
fun MyScreen(myViewModel: MyViewModelLiveData) {

    var count = myViewModel?.myCount?.value ?: 0
    myCountDisplay(count )

    Button(onClick = { myViewModel.incrementMyCount() }) {
        Text("Increment Counter")
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
    K2025_02_17_liveDataInViewModelTheme {
        Greeting("Android")
    }
}
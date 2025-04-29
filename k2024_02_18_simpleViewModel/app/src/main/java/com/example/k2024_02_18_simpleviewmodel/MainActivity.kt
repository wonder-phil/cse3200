package com.example.k2024_02_18_simpleviewmodel

import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.k2024_02_18_simpleviewmodel.ui.theme.K2024_02_18_simpleViewModelTheme

class MainActivity : ComponentActivity() {

    private val TAG = "PGB"

    lateinit var myViewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        myViewModel = ViewModelProvider(this)[MyViewModel::class.java]

        setContent {
            K2024_02_18_simpleViewModelTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(myViewModel,
                        "Android",
                            modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart() called: ${myViewModel.getMyCount()}")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume() called: ${myViewModel.getMyCount()}")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause() called: ${myViewModel.getMyCount()}")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop() called : ${myViewModel.getMyCount()}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy() called: : ${myViewModel.getMyCount()}")
    }

    override fun onSaveInstanceState(bundle: Bundle) {
        super.onSaveInstanceState(bundle)
        Log.i(TAG, "onSaveInstanceState() called with: bundle = ${bundle.keySet()}")
    }

}

@Composable
fun Greeting(myViewModel: MyViewModel, name: String, modifier: Modifier = Modifier) {

    var myLocalCount  = rememberSaveable { mutableStateOf(0) } //myViewModel.getMyCount()

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier) {
        Spacer(Modifier.height(20.dp))
        Button(onClick = { myLocalCount.value  = myViewModel.getMyCount() }) {
            Text(text = "Get myCount")
        }
        Text(
            text = "Hello $name!  The count is: ${myLocalCount.value}",
            modifier = modifier
        )
        Button(onClick = { myViewModel.incrementCount() } ) {
            Text("Increment count")
        }
    }
}

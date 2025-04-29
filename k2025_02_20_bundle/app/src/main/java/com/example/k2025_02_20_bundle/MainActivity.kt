package com.example.k2025_02_20_bundle

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.k2025_02_20_bundle.ui.theme.K2025_02_20_bundleTheme

class MainActivity : ComponentActivity() {

    private val TAG = "PGB"

    var myModel: MyModel = MyModel()
    init {
        myModel.myInt = 34
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        println("PGB" + savedInstanceState.toString())

        setContent {
            K2025_02_20_bundleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState")

        outState.putParcelable("PGB", myModel)
        outState.putString ("PGB", "TEST")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG, "onRestoreInstanceState")

        Log.i(TAG, savedInstanceState.getString("PGB").toString())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            myModel = savedInstanceState.getParcelable("PGB", MyModel::class.java)!!
        }
        Log.i(TAG, myModel.myText + myModel.myInt.toString())
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
    K2025_02_20_bundleTheme {
        Greeting("Android")
    }
}
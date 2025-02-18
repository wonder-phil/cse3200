package com.example.k2025_02_15_viewmodelsavestatehandle

import android.app.Activity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
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
import androidx.lifecycle.ViewModelProvider
import com.example.k2025_02_15_viewmodelsavestatehandle.ui.theme.K2025_02_15_viewModelSaveStateHandleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myViewModelSaveHandle = ViewModelProvider(this)[MyViewModelSaveHandle::class.java]

        enableEdgeToEdge()
        setContent {
            K2025_02_15_viewModelSaveStateHandleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(this,
                        name = "Android",
                        myViewModelSaveHandle,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


    override fun onSaveInstanceState(bundle: Bundle) {
        super.onSaveInstanceState(bundle)
        Log.d("PGB", "onSaveInstanceState() called with: bundle = $bundle")
    }

}


@Composable
fun Greeting(activity: Activity, name: String, myViewModelSaveHandle: MyViewModelSaveHandle, modifier: Modifier) {
    Column {
        Text(
            text = "Hello $name! ${myViewModelSaveHandle.getMyCount()}",
            modifier = modifier
        )
        Button(onClick = { myViewModelSaveHandle.incrementMyCount() }) {
            Text(text = "Increment")
        }
        Button(onClick = { android.os.Process.killProcess(android.os.Process.myPid()); }) {
            Text(text = "Shudwon app")
        }
        Button(onClick = { System.runFinalization();
            activity.finish() }) {
            Text(text = "Finish app")
        }
    }
}

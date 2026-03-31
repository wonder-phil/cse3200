package com.example.k2026_03_30_unbound_service

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.k2026_03_30_unbound_service.DEFINITIONS.EXTRA_PARAM1
import com.example.k2026_03_30_unbound_service.DEFINITIONS.EXTRA_PARAM2
import com.example.k2026_03_30_unbound_service.ui.theme.K2026_03_30_unbound_serviceTheme
import com.example.k2026_03_30_unbound_service.ui.theme.MyWorker

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            K2026_03_30_unbound_serviceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        this,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, myContext: Context, modifier: Modifier = Modifier) {
    val data = workDataOf(
        "ACTION" to DEFINITIONS.ACTION_FOO,
        EXTRA_PARAM1 to "value1",
        EXTRA_PARAM2 to "value2"
    )
    val data1 = workDataOf(
        "ACTION" to DEFINITIONS.ACTION_BAZ,
        EXTRA_PARAM1 to "value1",
        EXTRA_PARAM2 to "value2"
    )

    val request = OneTimeWorkRequestBuilder<MyWorker>()
        .setInputData(data)
        .build()

    val request1 = OneTimeWorkRequestBuilder<MyWorker>()
        .setInputData(data1)
        .build()

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()) {
        Button({WorkManager.getInstance(myContext).enqueue(request)}) {
            Text("Start the Only job")
        }
        Button({WorkManager.getInstance(myContext).enqueue(request1)}) {
            Text("Start the Only job")
        }
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
}

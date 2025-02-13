package com.example.k2025_02_11_buttons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.k2025_02_11_buttons.ui.theme.K2025_02_11_buttonsTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_second)
        setContent {
            K2025_02_11_buttonsTheme {
                    myExample("Phil", { it -> println(it) } )
                }
            }
        }
    }



@Composable
fun myExample(name: String, fcn: (String) -> Unit) {
    var popUpSeen = remember { mutableStateOf(false) }
    Column(verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(0.9f)) {
        Button (
            onClick = {  popUpSeen.value = true } ,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Select Button")
        }
        if (popUpSeen.value) {
            AlertPopup()
        }
    }
}

@Composable
fun AlertPopup() {

    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside or presses the back button
                openDialog.value = false
            },
            title = { Text(text = "Alert Title") },
            text = { Text("This is an alert message.") },
            confirmButton = {
                Button(onClick = {
                    // Handle the confirm button press
                    openDialog.value = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(onClick = {
                    // Handle the dismiss button press
                    openDialog.value = false
                }) {
                    Text("Cancel")
                }
            }
        )
    }
}


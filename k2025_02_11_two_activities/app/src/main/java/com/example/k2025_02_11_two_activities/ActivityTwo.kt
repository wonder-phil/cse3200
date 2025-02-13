package com.example.k2025_02_11_two_activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityTwo : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LaunchPopUp()
        }
    }
}

@Composable
fun LaunchPopUp() {
    var showPopUp = remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize(0.9f))
    {
        Text("Hello world from Activity Two",
            fontSize = 30.sp)
        Button(onClick = { showPopUp.value = true },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
                contentColor = Color.Yellow
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Launch PopUp")
        }
        if (showPopUp.value) {
            AlertPopup()
            //showPopUp.value = false
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

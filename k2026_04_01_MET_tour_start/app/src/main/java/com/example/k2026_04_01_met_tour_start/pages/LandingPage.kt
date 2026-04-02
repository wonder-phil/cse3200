package com.example.k2026_04_01_met_tour_start.pages

import com.example.k2026_04_01_met_tour_start.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.k2026_04_01_met_tour_start.models.SearchStrings
import com.example.k2026_04_01_met_tour_start.routes.Routes

@Composable
fun LandingPage(
    goToDisplayPage: () -> Unit,
    goToHistoryPage: () -> Unit,
    modifier: Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize().padding(24.dp),
    ) {
        Spacer(modifier = Modifier.padding(vertical = 30.dp))
        Text("Landing Screen", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        MetFrontDoor()
        InputSearchTerm()
        Button(onClick = { goToDisplayPage() }) {
            modifier.testTag("goto_display_button")
            Text("Display Page")
        }

        Button(onClick = { goToHistoryPage() } ) {
            Text("History Page")
        }
    }
}

@Composable
fun MetFrontDoor() {
    Image(
        painter = painterResource(id = R.drawable.met_front_door),
        contentDescription = "MET front door",
        modifier = Modifier.fillMaxSize(0.25f),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun InputSearchTerm() {
    var text by remember { mutableStateOf("") }
    val searchStrings: SearchStrings = SearchStrings()
    Column {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Search term") },
            placeholder = { Text("Enter username") },
            singleLine = true
        )

        Text("Current value: $text")
        searchStrings.addSearchTerm("$text")
    }
}
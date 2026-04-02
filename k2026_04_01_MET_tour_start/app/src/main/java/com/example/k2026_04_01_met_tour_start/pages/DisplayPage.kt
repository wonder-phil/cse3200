package com.example.k2026_04_01_met_tour_start.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.k2026_04_01_met_tour_start.models.SearchStrings

@Composable
fun DisplayPage(
    goBack: () -> Unit
) {
    val searchStrings = SearchStrings()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize().padding(24.dp),
    ) {
        Spacer(modifier = Modifier.padding(vertical = 30.dp))
        Text("Display page", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Text("Search string: ${SearchStrings.searchStringList}")
        Button(onClick = goBack) {
            Text("Back")
        }
    }
}
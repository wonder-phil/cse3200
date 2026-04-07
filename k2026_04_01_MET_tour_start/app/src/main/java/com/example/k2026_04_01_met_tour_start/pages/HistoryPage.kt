package com.example.k2026_04_01_met_tour_start.pages

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.k2026_04_01_met_tour_start.models.SearchStrings

@Composable
fun HistoryPage(
    goBack: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize().padding(24.dp),

    ) {
        Spacer(modifier = Modifier.padding(vertical = 30.dp))
        Text("History", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.padding(vertical = 20.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(0.7f),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = SearchStrings.searchStringList,
                key = { it }   // stable key = smooth scrolling
            ) { historyItem ->

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    onClick = { Log.i("PGB","Card clicked ${historyItem}") }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "${historyItem}")
                        Spacer(modifier = Modifier.height(6.dp))
                    }
                }
            }
        }


        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Button(onClick = goBack, modifier = Modifier.testTag("history_back_button")) {
            Text("Back")
        }
    }
}
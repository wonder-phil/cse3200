package com.example.k2026_02_11_more_screen_navigation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QuizQuestionScreen(
    userId: Int,
    goBack: () -> Unit,
    goToSummaryScreen: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Quiz Question Screen", style = MaterialTheme.typography.headlineMedium)
        Text("UserId = $userId")
        Button(onClick = goBack) {
            Text("Back")
        }
        Button(onClick =  { goToSummaryScreen(userId) } ) {
            Text("To Summary Screen")
        }
    }
}
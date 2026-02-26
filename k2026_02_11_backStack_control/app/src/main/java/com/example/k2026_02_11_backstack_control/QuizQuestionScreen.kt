package com.example.k2026_02_11_backstack_control

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.k2026_02_11_backstack_control.controllers.ComputeNextQuestion
import com.example.k2026_02_11_backstack_control.controllers.NextQuestion
import com.example.k2026_02_11_backstack_control.models.ListOfBooleanQuestions
import com.example.k2026_02_11_backstack_control.models.Score

@Composable
fun QuizQuestionScreen(
    goToSummaryScreen: () -> Unit
) {
    var qIndex = rememberSaveable { mutableStateOf<Int>(0) }
    val nextQuestion = NextQuestion(ComputeNextQuestion.LINEAR, qIndex)
    var isTrue = ListOfBooleanQuestions.listOfBooleanQuestions[qIndex.value].isTrue

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Quiz Question Screen", style = MaterialTheme.typography.headlineMedium)
        Column(horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = ListOfBooleanQuestions.listOfBooleanQuestions[qIndex.value].text)
                    Spacer(modifier = Modifier.height(6.dp))
                    Row() {
                        Button(onClick = {  if (isTrue) Score.incrementTotalScore(); nextQuestion.getQuestionIndex() }) {
                            Text("True")
                        }
                        Button(onClick = { if (!isTrue) Score.incrementTotalScore(); nextQuestion.getQuestionIndex() }) {
                            Text("False")
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Button(onClick = { nextQuestion.getQuestionIndex() }) {
                        Text("Skip this question")
                    }
                }
            }
        }
        Button(onClick =  { goToSummaryScreen() } ) {
            Text("To Summary Screen")
        }
    }
}
package com.example.k2026_02_09_lazy_list

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.k2026_02_09_lazy_list.ui.theme.K2026_02_09_lazy_listTheme
import models.BooleanQuestion
import models.ListOfBooleanQuestions

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var myList = ListOfBooleanQuestions()
        setContent {
            K2026_02_09_lazy_listTheme {
                BooleanQuestionList(myList.getListOfBooleanQuestions())
            }
        }
    }
}

@Composable
fun BooleanQuestionList(questions: List<BooleanQuestion>, ) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = questions,
            key = { it.number }   // stable key = smooth scrolling
        ) { question ->

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                onClick = { Log.i("PGB","Card clicked ${question.number}") }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "${question.number}. ${question.text}")
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = "Type: ${question.questionType}")
                }
            }
        }
    }
}


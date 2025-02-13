package com.example.k2025_02_13_quiz_lab01

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.k2025_02_13_quiz_lab01.model.ListOfBooleanQuestions
import com.example.k2025_02_13_quiz_lab01.ui.theme.K2025_02_13_quiz_lab01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            K2025_02_13_quiz_lab01Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    QuestionScreen(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun QuestionScreen(name: String, modifier: Modifier = Modifier) {
    var currentQuestion = remember {  mutableStateOf<Int>(ListOfBooleanQuestions.getCurrentQuestionIndex()) }

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.safeContentPadding()
    )
    {

        Text(
            text = "Hello $name!  The quiz is ready",
            fontSize = 40.sp,
            modifier = modifier
        )
        Spacer(Modifier.height(30.dp))
        Text(
            text = ListOfBooleanQuestions.getBooleanQuestion(currentQuestion.value++).questionText,
            fontSize = 34.sp,
            modifier = modifier
        )
        Spacer(Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
        ) {
            Button(onClick = { ListOfBooleanQuestions.nextQuestionIndex() }) {
                Text(
                    text = "True",
                    fontSize = 24.sp,
                    modifier = modifier
                )
            }
            Button(onClick = {}) {
                Text(
                    text = "False",
                    fontSize = 24.sp,
                    modifier = modifier
                )
            }
            Button(onClick = {}) {
                Text(
                    text = "Skip",
                    fontSize = 24.sp,
                    modifier = modifier
                )
            }
        }
        Spacer(Modifier.height(50.dp))
        Row(
            horizontalArrangement = Arrangement.End,
        )
        {
            Button(onClick = {}) {
                Text(
                    text = "Done",
                    fontSize = 24.sp,
                    modifier = modifier
                )
            }
        }
    }
}

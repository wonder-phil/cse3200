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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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
            style = TextStyle(
                fontSize = 28.sp,
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                letterSpacing = 1.5.sp,
                shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.7f),
                    offset = Offset(1.25f, 1.25f),
                    blurRadius = 4f
                )
            ),
            modifier = Modifier.padding(16.dp)
        )
        Spacer(Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
        ) {

            ElevatedButton(
                onClick = { ListOfBooleanQuestions.nextQuestionIndex() },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black
                ),
            ) {
                Text("True",
                    fontSize = 32.sp,
                    color = Color.Black,
                    modifier = modifier)
            }

            ElevatedButton(
                onClick = {  },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black
                ),
            ) {
                Text("False",
                    fontSize = 32.sp,
                    color = Color.Black,
                    modifier = modifier)
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
            ElevatedButton(onClick = {},
                ) {
                Text(
                    text = "Done",
                    fontSize = 24.sp,
                    color = Color.Yellow,
                    modifier = modifier
                )
            }
        }
    }
}

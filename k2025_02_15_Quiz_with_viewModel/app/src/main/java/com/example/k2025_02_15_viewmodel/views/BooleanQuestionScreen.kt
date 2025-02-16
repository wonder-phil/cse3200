package com.example.k2025_02_15_viewmodel.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.Text

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.k2025_02_15_viewmodel.model.BooleanQuestion
import com.example.k2025_02_15_viewmodel.model.QuestionType
import com.example.k2025_02_15_viewmodel.model.booleanQuestionsList
import com.example.k2025_02_15_viewmodel.views.ui.theme.BooleanQuestionsAppTheme

@Composable
fun BooleanQuestionScreen() {
    LazyColumn(
        contentPadding = PaddingValues(
            vertical = 8.dp,
            horizontal = 8.dp
        )
    ) {
        items(booleanQuestionsList) { booleanQuestion ->
            BooleanQuestionItem(booleanQuestion)
        }
    }
}

@Composable
fun BooleanQuestionItem(item: BooleanQuestion) {

    var checked by rememberSaveable { mutableStateOf(false) }

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Column {
                QuestionIcon(Icons.Filled.Place, Modifier.weight(0.25f))
                Checkbox(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    // Customize checkbox colors using MaterialTheme's color scheme
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.primary,
                        uncheckedColor = MaterialTheme.colorScheme.onSurface,
                        checkmarkColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }

            BooleanQuestionDetails(item.text, item.questionType, Modifier.weight(0.85f))
            Column() {
                Button(onClick = {}, Modifier.background(color = Color.LightGray)) {
                    Text(text = "True",
                        color = Color.White)
                }
                Button(onClick = {}, Modifier.background(color = Color.LightGray)) {
                    Text(text = "False",
                        color = Color.White)
                }
            }

        }
    }
}


@Composable
private fun QuestionIcon(icon: ImageVector, modifier: Modifier) {
    Image(
        imageVector = icon,
        contentDescription = "Question icon",
        modifier = modifier.padding(8.dp)
    )
}

@Composable
private fun BooleanQuestionDetails(myText: String, questionType: QuestionType, modifier: Modifier) {
        Text(text = myText,
            fontSize = 16.sp,
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier)
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BooleanQuestionsAppTheme {
        BooleanQuestionScreen()
    }
}
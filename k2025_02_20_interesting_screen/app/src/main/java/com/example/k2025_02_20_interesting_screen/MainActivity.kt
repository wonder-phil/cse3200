package com.example.k2025_02_20_interesting_screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.k2025_02_20_interesting_screen.ui.theme.K2025_02_20_interesting_screenTheme


import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab01App()
        }
    }
}

@PreviewScreenSizes
@Composable
fun Lab01App() {
    var currentScreen by rememberSaveable { mutableStateOf<Screen>(Screen.Start) }

    when (val screen = currentScreen) {
        is Screen.Start -> {
            StartScreen(
                onStartClick = { currentScreen = Screen.Quiz }
            )
        }

        is Screen.Quiz -> {
            QuizScreen(
                onDone = { score ->
                    currentScreen = Screen.Result(score)
                }
            )
        }

        is Screen.Result -> {
            ResultScreen(
                score = screen.score,
                onRestart = { currentScreen = Screen.Start }
            )
        }
    }
}

sealed class Screen {
    object Start : Screen()
    object Quiz : Screen()
    data class Result(val score: Int) : Screen()
}


@Composable
fun StartScreen(onStartClick: () -> Unit) {
    Scaffold { padding ->
        androidx.compose.foundation.layout.Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to the Quiz")

            androidx.compose.material3.Button(
                onClick = onStartClick
            ) {
                Text("Start Quiz")
            }
        }
    }
}


@Composable
fun QuizScreen(onDone: (Int) -> Unit) {
    var score by rememberSaveable { mutableStateOf(0) }

    Scaffold { padding ->
        androidx.compose.foundation.layout.Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(16.dp),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Text("Mars is the third planet from the sun")

            androidx.compose.material3.Button(
                onClick = { score++ }
            ) {
                Text("True")
            }

            androidx.compose.material3.Button(
                onClick = { /* no point */ }
            ) {
                Text("False")
            }

            androidx.compose.material3.Button(
                onClick = { onDone(score) }
            ) {
                Text("Done")
            }
        }
    }
}

@Composable
fun ResultScreen(score: Int, onRestart: () -> Unit) {
    Scaffold { padding ->
        androidx.compose.foundation.layout.Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
        ) {
            Text("Your score is: $score")

            androidx.compose.material3.Button(
                onClick = onRestart
            ) {
                Text("Restart")
            }
        }
    }
}


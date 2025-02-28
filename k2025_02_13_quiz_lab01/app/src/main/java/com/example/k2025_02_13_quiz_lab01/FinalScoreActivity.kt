package com.example.k2025_02_13_quiz_lab01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.k2025_02_13_quiz_lab01.model.Score

class FinalScoreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val myScore = Score.totalSkipped.toString() ?: "None yet"
        val myIntent = this.intent

        setContent {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize(0.9f)) {

                Text(text = "Score: ${myScore}",
                    fontSize = 32.sp)

                Text(text = "Score: ${myIntent.getStringExtra("totalSkipped").toString()}",
                    fontSize = 32.sp)

            }

        }
    }
}
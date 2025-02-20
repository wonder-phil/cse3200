package com.example.k2025_02_14_buttons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity

import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.k2025_02_14_buttons.ui.theme.K2025_02_14_buttonsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            K2025_02_14_buttonsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ButtonTest(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

class ListOfTextItems {
    companion object {
        val myList = listOf(
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six")
    }

}

@Composable
fun ButtonTest(name: String, modifier: Modifier = Modifier) {
    val listIndex = remember { mutableStateOf(0) }
    val insets = LocalWindowInsets.current
    //val insets =  WindowInsets.systemBars

    Column(verticalArrangement = Arrangement.Top,
        modifier = Modifier
        .systemBarsPadding()
    ) {
        val density = LocalDensity.current

        val leftInset = with(density) { insets.systemBars.left.toDp() }
        val rightInset = with(density) { insets.systemBars.right.toDp() }
        val topInset = with(density) { insets.systemBars.top.toDp() }
        val bottomInset = with(density) { insets.systemBars.bottom.toDp() }

        val configuration = LocalConfiguration.current
        val screenWidthDp = configuration.screenWidthDp.dp
        val screenHeightDp = configuration.screenHeightDp.dp

        // Calculate safe area dimensions
        val safeAreaWidth = screenWidthDp - leftInset - rightInset
        val safeAreaHeight = screenHeightDp - topInset - bottomInset
        // Convert the top inset from pixels to Dp
        val safeAreaTop = with(LocalDensity.current) { insets.systemBars.top.toDp() }

        val offsetY = safeAreaHeight / 5

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .offset(y = offsetY)
        ) {
            Text(
                text = "Hello $name, my number is ${ListOfTextItems.myList[listIndex.value]}!",
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

            ElevatedButton(
                onClick = { listIndex.value = ++listIndex.value % ListOfTextItems.myList.size },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black
                ),
            ) {
                Text("Next number",
                    fontSize = 32.sp,
                    color = Color.Black,
                    modifier = modifier)
            }
        }
    }
}



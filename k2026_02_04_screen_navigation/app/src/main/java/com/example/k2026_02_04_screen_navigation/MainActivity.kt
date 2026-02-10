package com.example.k2026_02_04_screen_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.k2026_02_04_screen_navigation.ui.theme.K2026_02_04_screen_navigationTheme


object Routes {
    const val LANDING_SCREEN = "landing_screen"
    const val QUIZ_QUESTIONS = "quiz_questions"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = com.example.k2026_02_04_screen_navigation.Routes.LANDING_SCREEN
            ) {
                composable(com.example.k2026_02_04_screen_navigation.Routes.LANDING_SCREEN) {
                    LandingScreen(
                        goToQuizScreen = { userId ->
                            navController.navigate("${com.example.k2026_02_04_screen_navigation.Routes.QUIZ_QUESTIONS}/$userId")
                        }
                    )
                }

                composable(
                    route = "${com.example.k2026_02_04_screen_navigation.Routes.QUIZ_QUESTIONS}/{userId}",
                    arguments = listOf(
                        navArgument("userId") { type = NavType.IntType }
                    )
                ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getInt("userId") ?: -1
                    QuizQuestionScreen(
                        userId = userId,
                        goBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    K2026_02_04_screen_navigationTheme {
        Greeting("Android")
    }
}
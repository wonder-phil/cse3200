package com.example.k2026_02_11_more_screen_navigation

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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.k2026_02_11_more_screen_navigation.ui.theme.K2026_02_11_more_screen_navigationTheme

object Routes {
    const val LANDING_SCREEN = "landing_screen"
    const val QUIZ_QUESTIONS = "quiz_questions"
    const val SUMMARY_SCREEN = "summary_screen"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = Routes.LANDING_SCREEN
            ) {
                composable(Routes.LANDING_SCREEN) {
                    LandingScreen(
                        goToQuizScreen = { userId ->
                            navController.navigate("${Routes.QUIZ_QUESTIONS}/$userId")
                        }
                    )
                }

                composable(
                    route = "${Routes.QUIZ_QUESTIONS}/{userId}",
                    arguments = listOf(
                        navArgument("userId") { type = NavType.IntType }
                    )
                ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getInt("userId") ?: -1
                    QuizQuestionScreen(
                        userId = userId,
                        goBack = { navController.popBackStack() },
                        goToSummaryScreen = { userId: Int ->
                            navController.navigate("${Routes.SUMMARY_SCREEN}/$userId")
                        }
                    )
                }

                composable(
                    route = "${Routes.SUMMARY_SCREEN}/{userId}",
                    arguments = listOf(
                        navArgument("userId") { type = NavType.IntType }
                    )
                ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getInt("userId") ?: -1
                   SummaryScreen(
                        userId = userId,
                        goBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}


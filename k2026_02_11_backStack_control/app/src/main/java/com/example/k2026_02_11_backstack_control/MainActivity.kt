package com.example.k2026_02_11_backstack_control

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
import com.example.k2026_02_11_backstack_control.ui.theme.K2026_02_11_backStack_controlTheme

object Routes {
    const val LANDING_SCREEN = "landing_screen"
    const val QUIZ_QUESTIONS = "quiz_questions"
    const val SUMMARY_SCREEN = "summary_screen"

    const val QUIZ_ROUTE = "$QUIZ_QUESTIONS/{userId}"
    const val SUMMARY_ROUTE = "$SUMMARY_SCREEN/{userId}"
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            setContent {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Routes.LANDING_SCREEN
                ) {

                    /* LANDING_SCREEN */
                    composable(Routes.LANDING_SCREEN) {
                        LandingScreen(
                            goToQuizScreen = {
                                navController.navigate("${Routes.QUIZ_QUESTIONS}/") {
                                    popUpTo(0)
                                }
                            }
                        )
                    }

                    /* QUIZ_QUESTIONS */
                    composable(
                        route = "${Routes.QUIZ_QUESTIONS}/",
                    ) {
                        QuizQuestionScreen(
                            goToSummaryScreen = {
                                navController.navigate("${Routes.SUMMARY_SCREEN}/") {
                                    popUpTo(0)
                                }
                            }
                        )
                    }

                    /* SUMMARY_SCREEN */
                    composable(
                        route = "${Routes.SUMMARY_SCREEN}/",

                    ) {
                        SummaryScreen(
                            restartQuiz = { navController.navigate(Routes.LANDING_SCREEN) {
                                popUpTo(0) // nuke entire backstack
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

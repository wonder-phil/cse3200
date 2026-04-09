package com.example.k2026_04_01_met_tour_start

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
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.k2026_04_01_met_tour_start.pages.DisplayPage
import com.example.k2026_04_01_met_tour_start.pages.HistoryPage
import com.example.k2026_04_01_met_tour_start.pages.LandingPage
import com.example.k2026_04_01_met_tour_start.routes.Routes
import com.example.k2026_04_01_met_tour_start.ui.theme.K2026_04_01_MET_tour_startTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            AppNavHost(navController)

        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.LANDING_PAGE
    ) {
        composable(Routes.LANDING_PAGE) {
            LandingPage(
                goToDisplayPage = { navController.navigate("${Routes.DISPLAY_PAGE}") },
                goToHistoryPage = { navController.navigate("${Routes.HISTORY_PAGE}") },
                modifier = Modifier
            )
        }

        composable(route = "${Routes.DISPLAY_PAGE}") { backStackEntry ->
            DisplayPage(
                goBack = { navController.popBackStack() }
            )
        }

        composable(route = "${Routes.HISTORY_PAGE}" ) { backStackEntry ->
            HistoryPage (
                goBack = { navController.popBackStack() })
        }
    }

}

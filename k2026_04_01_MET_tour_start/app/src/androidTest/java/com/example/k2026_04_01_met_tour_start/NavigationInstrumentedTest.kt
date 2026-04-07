package com.example.k2026_04_01_met_tour_start

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.k2026_04_01_met_tour_start.routes.Routes

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class AppNavHostInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    private fun setNavContent() {
        composeTestRule.setContent {
            val context = LocalContext.current
            navController = TestNavHostController(context).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            AppNavHost(navController)
        }
    }

    @Test
    fun startsAtLandingPage() {
        setNavContent()
        assertEquals(Routes.LANDING_PAGE, navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun landingToDisplay() {
        setNavContent()
        composeTestRule.onNodeWithTag("goto_display_button").performClick()
        assertEquals(Routes.DISPLAY_PAGE, navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun landingToHistory() {
        setNavContent()
        composeTestRule.onNodeWithTag("goto_history_button").performClick()
        assertEquals(Routes.HISTORY_PAGE, navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun displayBackToLanding() {
        setNavContent()
        composeTestRule.onNodeWithTag("goto_display_button").performClick()
        composeTestRule.onNodeWithTag("display_back_button").performClick()
        assertEquals(Routes.LANDING_PAGE, navController.currentBackStackEntry?.destination?.route)
    }

    @Test
    fun historyBackToLanding() {
        setNavContent()
        composeTestRule.onNodeWithTag("goto_history_button").performClick()
        composeTestRule.onNodeWithTag("history_back_button").performClick()
        assertEquals(Routes.LANDING_PAGE, navController.currentBackStackEntry?.destination?.route)
    }
}
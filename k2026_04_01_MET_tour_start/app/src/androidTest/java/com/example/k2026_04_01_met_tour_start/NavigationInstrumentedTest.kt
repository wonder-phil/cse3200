package com.example.k2026_04_01_met_tour_start

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
class NavigationInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>() //createComposeRule() //

    @Test
    fun clickingButton_navigatesToDetails() {
        lateinit var navController: TestNavHostController

        composeTestRule.setContent {
            val context = LocalContext.current

            navController = TestNavHostController(context).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }

            AppNavHost(navController = navController)
        }

        // Confirm start destination
        // navController.currentBackStackEntry?.destination?.route
        assertEquals(Routes.LANDING_PAGE, navController.navigate(Routes.LANDING_PAGE))


        composeTestRule.onNodeWithTag("goto_display_button").performClick()
        // Confirm details UI appears
        composeTestRule.onNodeWithText("Details Screen").assertIsDisplayed()
    }
}
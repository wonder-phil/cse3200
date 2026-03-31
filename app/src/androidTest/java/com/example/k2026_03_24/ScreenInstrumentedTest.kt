package com.example.k2026_03_24


import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which MUST execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AndroidJUnit4::class)
class MainActivityComposeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun button_click_changes_text() {
        // Verify initial state
        composeTestRule
            .onNodeWithText("Select me")
            .assertExists()

        // Perform click
        composeTestRule
            .onNodeWithText("Select me")
            .performClick()

        composeTestRule
            .onNodeWithText("Button clicked")
            .assertExists()
    }
}
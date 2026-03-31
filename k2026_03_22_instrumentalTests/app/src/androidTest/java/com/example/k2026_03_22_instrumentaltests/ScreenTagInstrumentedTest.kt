package com.example.k2026_03_22_instrumentaltests

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScreenTagInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun button_click_updates_text_with_tags() {

        composeTestRule
            .onNodeWithTag("main_text")
            .assertTextEquals("Button not clicked")

        composeTestRule
            .onNodeWithTag("button")
            .performClick()

        composeTestRule
            .onNodeWithTag("main_text")
            .assertTextEquals("Button clicked")
    }

    @Test
    fun button_click_updates_text_with_tags2() {

        composeTestRule
            .onNodeWithTag("main_text")
            .assertTextEquals("Button not clicked")
    }
}
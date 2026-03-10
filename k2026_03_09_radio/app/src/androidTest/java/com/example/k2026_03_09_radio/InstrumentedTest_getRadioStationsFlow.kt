package com.example.k2026_03_09_radio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.k2026_03_09_radio.ViewModels.RadioStationViewModel

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class InstrumentedTest_getRadioStationsFlow {

    @Test
    @Composable
    fun getManyRadioStations() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val radioStatios = RadioStationViewModel().radioStationsFlow.collectAsState(initial =  emptyList())
        //assertEquals(radioStatios.toString(),
    }

    
}
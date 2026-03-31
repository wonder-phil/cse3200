package com.example.k2026_03_23_sensors

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


@RunWith(AndroidJUnit4::class)
class RotationLikeSensorTest {

    @Test
    fun tiltAroundAxes() {
        // “Face up / near rest” style reading
        EmulatorSensors.setAcceleration(0f, 0f, 9.8f)

        // Simulate tilt toward +X
        EmulatorSensors.setAcceleration(5f, 0f, 8.4f)

        // Simulate tilt toward +Y
        EmulatorSensors.setAcceleration(0f, 5f, 8.4f)

        // In your app, verify the resulting sensor behavior here
    }
}
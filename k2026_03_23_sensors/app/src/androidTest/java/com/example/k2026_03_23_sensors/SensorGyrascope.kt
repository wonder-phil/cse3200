package com.example.k2026_03_23_sensors

import android.app.Instrumentation
import androidx.test.platform.app.InstrumentationRegistry
import java.io.BufferedReader
import java.io.InputStreamReader

object EmulatorSensors {
    private fun exec(cmd: String): String {
        val instrumentation: Instrumentation =
            InstrumentationRegistry.getInstrumentation()

        val pfd = instrumentation.uiAutomation.executeShellCommand(cmd)
        pfd.use { parcelFileDescriptor ->
            BufferedReader(InputStreamReader(
                java.io.FileInputStream(parcelFileDescriptor.fileDescriptor)
            )).use { reader ->
                return reader.readText()
            }
        }
    }

    fun setAcceleration(x: Float, y: Float, z: Float) {
        exec("""adb emu sensor set acceleration $x:$y:$z""")
    }

    fun getAcceleration(): String {
        return exec("""adb emu sensor get acceleration""")
    }
}
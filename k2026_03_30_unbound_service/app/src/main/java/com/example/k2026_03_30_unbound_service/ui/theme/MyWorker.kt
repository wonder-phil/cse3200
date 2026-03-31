package com.example.k2026_03_30_unbound_service.ui.theme

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.k2026_03_30_unbound_service.DEFINITIONS
import com.example.k2026_03_30_unbound_service.DEFINITIONS.ACTION_BAZ
import com.example.k2026_03_30_unbound_service.DEFINITIONS.EXTRA_PARAM1
import com.example.k2026_03_30_unbound_service.DEFINITIONS.EXTRA_PARAM2

class MyWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val action = inputData.getString("ACTION")

        return when (action) {
            DEFINITIONS.ACTION_FOO -> {
                val param1 = inputData.getString(EXTRA_PARAM1)
                val param2 = inputData.getString(EXTRA_PARAM2)
                handleActionFoo(param1, param2)
                Result.success()
            }

            ACTION_BAZ -> {
                val param1 = inputData.getString(EXTRA_PARAM1)
                val param2 = inputData.getString(EXTRA_PARAM2)
                handleActionBaz(param1, param2)
                Result.success()
            }

            else -> Result.failure()
        }
    }

    private fun handleActionFoo(param1: String?, param2: String?) {
        Log.i("PGB", " handleActionFoo")
    }
    private fun handleActionBaz(param1: String?, param2: String?) {
        Log.i("PGB", " handleActionBaz")
    }
}
package com.example.k2026_03_26_bound_service.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log


class LocalService : Service() {
    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService(): LocalService = this@LocalService
    }

    override fun onBind(intent: Intent): IBinder {
        Log.i("PGB", "bind")
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i("PGB", "un-bind")
        return super.onUnbind(intent)
    }

    fun generateRandomNumber(): Int {
        Log.i("PGB", "generateRandomNumber")
        return (1..100).random()
    }
}

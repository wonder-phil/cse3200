package com.example.k2025_04_01_bound_service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MyService : Service() {

    // Binder given to clients
    private val binder = LocalBinder()

    // Class used for the client Binder.
    inner class LocalBinder : Binder() {
        fun getService(): MyService = this@MyService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    // Example method in the service
    fun generateRandomNumber(): Int {
        return (1..100).random()
    }
}

package com.example.k2025_03_27_randomnumberservice.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.util.Random


class LocalService : Service() {
    // Binder given to clients.
    // Binder given to clients
    private val binder = LocalBinder()

    // Class used for the client Binder.
    inner class LocalBinder : Binder() {
        fun getService(): LocalService = this@LocalService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    // Example method in the service
    fun generateRandomNumber(): Int {
        return (1..100).random()
    }
}
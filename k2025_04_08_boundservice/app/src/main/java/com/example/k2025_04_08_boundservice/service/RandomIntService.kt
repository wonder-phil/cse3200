package com.example.k2025_04_08_boundservice.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class RandomIntService : Service() {

    private val binder: RandomBinder = RandomBinder()

    inner class RandomBinder : Binder() {
        fun getService(): RandomIntService = this@RandomIntService
    }

    fun generateRandomInt(): Int {
        return (1..100).random()
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }
}
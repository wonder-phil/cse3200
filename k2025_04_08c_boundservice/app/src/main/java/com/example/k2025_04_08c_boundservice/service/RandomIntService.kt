package com.example.k2025_04_08c_boundservice.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class RandomIntService : Service() {

    var binder: Binder = RandomIntServiceBinder()

    inner class RandomIntServiceBinder : Binder() {
        fun getRandomIntService() : RandomIntService = this@RandomIntService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    fun getRandomNumber() : Int {
        return (1..100).random()
    }
}
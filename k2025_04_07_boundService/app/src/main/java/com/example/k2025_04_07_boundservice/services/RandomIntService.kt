package com.example.k2025_04_07_boundservice.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class RandomIntService : Service() {

    private var smallRandomInt: Int = 0
    private  val binder: LocalBinder = LocalBinder()

    inner class LocalBinder: Binder() {
        fun getService(): RandomIntService = this@RandomIntService
    }

    override fun onBind(intent: Intent) : IBinder {
        return binder
    }

    fun generateRandomInt() : Int {
        smallRandomInt = (1..100).random()
        return smallRandomInt
    }

}

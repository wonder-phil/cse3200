package com.example.k2026_04_05_simplelooperhandler

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log

class SimpleLooperHandler : Thread("Custom Thread") {
    var TAG = "PGB LOOPER_THREAD"
    lateinit var mHandler: Handler
    override fun run() {
        Looper.prepare()
        mHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                Log.d(TAG, "Looper name " + looper.thread.name)
                Log.d(TAG, "Args " + msg.arg1.toString() + " " + msg.arg2.toString())
                looper.thread.interrupt()
            }
        }
        Looper.loop()
    }
}
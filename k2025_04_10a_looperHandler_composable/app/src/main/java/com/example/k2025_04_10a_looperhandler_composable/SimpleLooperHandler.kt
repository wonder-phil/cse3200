package com.example.k2025_04_10a_looperhandler_composable


import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.TextView
import androidx.compose.runtime.MutableState

class SimpleLooperHandler : Thread("Custom Thread") {
    var TAG = "LOOPER_THREAD"
    lateinit var mHandler: Handler
    override fun run() {
        Looper.prepare()
        mHandler = object : Handler(Looper.getMainLooper()) {
            override fun handleMessage(msg: Message) {
                Log.d(TAG, "Looper name " + looper.thread.name)
                msg.arg1 += 3
                Log.d(TAG, "Args " + msg.arg1.toString() + " " + msg.arg2.toString())
                val textView = msg.obj as MutableState<String>
                textView.value = "Hello OBJ: " + msg.arg1.toString()
                looper.thread.interrupt()
            }
        }
        Looper.loop()
    }
}
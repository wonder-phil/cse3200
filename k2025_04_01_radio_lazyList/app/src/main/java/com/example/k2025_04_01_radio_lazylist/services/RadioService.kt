package com.example.k2025_04_01_radio_lazylist.services

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.widget.Toast
import com.example.k2025_04_01_radio_lazylist.models.RadioState
import com.example.k2025_04_01_radio_lazylist.models.RadioStationsManager

class RadioService : Service() {

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mMessenger: Messenger

    private var mService: Messenger? = null
    private var bound: Boolean = false

    private val mConnection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            //We are communicating with the
            // service using a Messenger, so here we get a client-side
            // representation of that from the raw IBinder object.
            mService = Messenger(service)
            bound = true
        }

        override fun onServiceDisconnected(className: ComponentName) {
            mService = null
            bound = false
        }
    }

    init {
        Intent(applicationContext, RadioService::class.java).also { intent ->
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    /**
     * Handler of incoming messages from clients.
     */
    internal class IncomingHandler(
        context: Context,
        private val applicationContext: Context = context.applicationContext
    ) : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                RadioState.PREPARE_ASYNC.intValue ->
                    Toast.makeText(applicationContext, "hello PREPARE_ASYNC", Toast.LENGTH_SHORT).show()
                RadioState.START.intValue ->
                    Toast.makeText(applicationContext, "hello START", Toast.LENGTH_SHORT).show()
                RadioState.STOP.intValue ->
                    Toast.makeText(applicationContext, "hello RadioState.STOP", Toast.LENGTH_SHORT).show()
                RadioState.DESTROY.intValue ->
                    Toast.makeText(applicationContext, "hello RadioState.DESTROY", Toast.LENGTH_SHORT).show()
                else -> super.handleMessage(msg)
            }
        }
    }

    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    override fun onBind(intent: Intent): IBinder? {
        Toast.makeText(applicationContext, "binding", Toast.LENGTH_SHORT).show()
        mMessenger = Messenger(IncomingHandler(this))
        return mMessenger.binder
    }

    private fun setUpRadio(myUrl: String) {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(myUrl)
            prepareAsync()
        }
    }
}
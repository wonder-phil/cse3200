package com.example.k2025_04_10_radioservice.services

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.widget.Toast

import com.example.k2025_04_10_radioservice.models.RadioStates

class RadioService : Service() {

    private lateinit var mMessenger: Messenger
    private var mediaPlayer: MediaPlayer? = null
    private val url_UConn = "http://stream.whus.org:8000/whusfm"

    /**
     * Handler of incoming messages from clients.
     */

    val handler = Handler(Looper.getMainLooper()) { msg ->
        when (msg.what) {
            RadioStates.SETUP_RADIO.stateInteger -> {
                Log.i("Service ", RadioStates.SETUP_RADIO.stateInteger.toString())
                setUpRadio(url_UConn)
                true
            }
            RadioStates.PREPARE_ASYNC.stateInteger -> {
                Log.i("Service ", RadioStates.PREPARE_ASYNC.stateInteger.toString())
                mediaPlayer?.prepareAsync()
                true
            }
            RadioStates.START.stateInteger -> {
                Log.i("Service ", RadioStates.START.stateInteger.toString())
                mediaPlayer?.start()
                true
            }
            RadioStates.STOP.stateInteger -> {
                Log.i("Service ", RadioStates.STOP.stateInteger.toString())
                mediaPlayer?.stop()
                true
            }
            RadioStates.DESTROY.stateInteger -> {
                Log.i("Service ", RadioStates.DESTROY.stateInteger.toString())
                mediaPlayer?.release()
                mediaPlayer = null
                true
            }
            else -> {
                Log.i("Service ", RadioStates.START.stateInteger.toString())
                true
                }
            }
        }




    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    override fun onBind(intent: Intent): IBinder? {
        Toast.makeText(applicationContext, "binding", Toast.LENGTH_SHORT).show()
        mMessenger = Messenger(handler)
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
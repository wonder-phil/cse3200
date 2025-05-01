package com.example.k2025_04_30_video

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.media3.exoplayer.ExoPlayer

class VideoViewModel(application: Application) : AndroidViewModel(application) {
    val player = ExoPlayer.Builder(application).build()

    override fun onCleared() {
        super.onCleared()
        player.release()
    }
}

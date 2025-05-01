package com.example.k2025_04_28_mediaservice.services


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaSession
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSessionService
import androidx.media3.session.SessionCommand
import androidx.media3.session.SessionResult
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.ListenableFuture

class VideoService : MediaSessionService() {
    private var mediaSession: MediaSession? = null

    val videoUrlNasa = "https://images-assets.nasa.gov/video/JPL-20190326-TECHf-0001-Mars%20Helicopter%20VF/JPL-20190326-TECHf-0001-Mars%20Helicopter%20VF~orig.mp4" //""http://stream.whus.org:8000/whusf"
    val videoUrlElephant = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4" //""http://stream.whus.org:8000/whusf"


    // Create your player and media session in the onCreate lifecycle event
    override fun onCreate() {
        super.onCreate()
        val player = ExoPlayer.Builder(this).build().apply {
            setMediaItem(
                MediaItem.Builder()
                    .setUri(videoUrlElephant)  // or any URI
                    .build()
            )
            prepare()
            playWhenReady = false // true
        }
        mediaSession = MediaSession.Builder(this, player)
    .setCallback(object : MediaSession.Callback {
        // Whitelist the default player commands + our custom "LIKE"
        @OptIn(UnstableApi::class)
        override fun onConnect(
            session: MediaSession,
            controller: MediaSession.ControllerInfo,
        ): MediaSession.ConnectionResult {
            val custom = SessionCommand("LIKE", Bundle.EMPTY)
            val sessionCommands = MediaSession.ConnectionResult.DEFAULT_SESSION_COMMANDS
                .buildUpon()
                .add(custom)
                .build()

            return MediaSession.ConnectionResult.AcceptedResultBuilder(session)
                .setAvailablePlayerCommands(Player.Commands.EMPTY)
                .setAvailableSessionCommands(sessionCommands)
                .build()
        }

        // Handle incoming commands
        override fun onCustomCommand(
            session: MediaSession,
            controller: MediaSession.ControllerInfo,
            customCommand: SessionCommand,
            args: Bundle,
        ): ListenableFuture<SessionResult> {
            return if (customCommand.customAction == "LIKE") {
                // <-- your "like" logic here
                //likeCurrentItem()
                Log.i("PGB", "Like received back in Video Service")
                player.play()
                Futures.immediateFuture(SessionResult(SessionResult.RESULT_SUCCESS))
            } else {
                super.onCustomCommand(session, controller, customCommand, args)
            }
        }
    })
    .build()

    // Tell the system about our session
    //sessionToken = session.sessionToken
    }

    // Remember to release the player and media session in onDestroy
    override fun onDestroy() {
        mediaSession?.run {
            player.release()
            release()
            mediaSession = null
        }
        super.onDestroy()
    }

    override fun onGetSession(controllerInfo: androidx.media3.session.MediaSession.ControllerInfo):
            androidx.media3.session.MediaSession? = mediaSession


    @OptIn(UnstableApi::class)
    override fun onTaskRemoved(rootIntent: Intent?) {
        pauseAllPlayersAndStopSelf()
    }
}

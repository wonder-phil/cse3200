package com.example.k2025_04_28_mediaservice

import android.content.ComponentName
import android.content.Context
import androidx.media3.session.MediaController
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.media3.session.MediaBrowser
import androidx.media3.session.MediaSessionService
import androidx.media3.session.SessionCommand
import androidx.media3.session.SessionCommands
import androidx.media3.session.SessionToken
import com.example.k2025_04_28_mediaservice.services.VideoService
import com.example.k2025_04_28_mediaservice.ui.theme.K2025_04_28_mediaServiceTheme
import com.google.common.util.concurrent.MoreExecutors
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class MainActivity : ComponentActivity() {

    private lateinit var mediaController: MediaController

    val likeCommand = SessionCommand("LIKE", Bundle().apply {
        putString("itemId", "42")
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val sessionToken =
            SessionToken(this, ComponentName(this, VideoService::class.java))

        val controllerFuture =
            MediaController.Builder(this, sessionToken).buildAsync()
        controllerFuture.addListener({
            // MediaController is available here with controllerFuture.get()
            val controller = controllerFuture.get()
            val sessionCommands: SessionCommands = controller.availableSessionCommands
            val commands: Set<SessionCommand> = sessionCommands.commands
            commands.forEach { cmd ->
                if (cmd.commandCode == SessionCommand.COMMAND_CODE_CUSTOM) {
                    Log.d("PGB", "Custom command = ${cmd.customAction}")
                } else {
                    Log.d("PGB", "Built-in command code = ${cmd.commandCode}")
                }
            }

            //controllerFuture.get().sendCustomCommand()
            mediaController.sendCustomCommand(likeCommand, Bundle())
                .addListener({
                    // this runs on the MAIN executor once the service replies
                    // you can inspect the returned SessionResult if you like
                    Log.i("PGB", "Like received back in Activity")
                }, ContextCompat.getMainExecutor(this))

        }, MoreExecutors.directExecutor())



        setContent {
            K2025_04_28_mediaServiceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    K2025_04_28_mediaServiceTheme {
        Greeting("Android")
    }
}
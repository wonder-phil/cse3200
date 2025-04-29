package com.example.k2025_04_22_met_museum

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.media.Image
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Canvas
//import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.k2025_04_22_met_museum.models.ImageContainer
import com.example.k2025_04_22_met_museum.ui.theme.K2025_04_22_MET_MuseumTheme
import com.example.k2025_04_22_met_museum.viewModels.ImageViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val imageViewModel = ViewModelProvider(this)[ImageViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            K2025_04_22_MET_MuseumTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        imageViewModel,
                        modifier = Modifier.padding(innerPadding),
                        this
                    )
                }
            }
        }
    }
}

fun drawableToPainter(drawable: Drawable): Painter {
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth.takeIf { it > 0 } ?: 1,
        drawable.intrinsicHeight.takeIf { it > 0 } ?: 1,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return BitmapPainter(bitmap.asImageBitmap())
}

fun uriToDrawable(context: Context, uri: Uri): Drawable? {
    return try {
        val inputStream = context.contentResolver.openInputStream(uri)
        Drawable.createFromStream(inputStream, uri.toString())
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}


@Composable
fun UriImage(uri: Uri, context: Context) {
    val painter = remember(uri) {
        uriToDrawable(context, uri)?.let { drawableToPainter(it) }
    }

    painter?.let {
        Image(painter = it, contentDescription = null)
    }
}



@Composable
fun Greeting(name: String, imageViewModel: ImageViewModel, modifier: Modifier = Modifier, context: Context) {
    var oneImage = remember { mutableStateOf <ImageContainer>(ImageContainer()) }
    Column(verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(0.9f)) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
        UriImage(Uri.parse("https://images.metmuseum.org/CRDImages/ep/web-large/DT1567.jpg"), context)



        /*
        Image(
            painter = drawableToPainter(oneImage.value?.getDrawable()!!),
            contentDescription = stringResource(R.string.famous_image)
        )

         */
        Button( onClick = {  }) {
            Text("Get image")
        }
    }

}

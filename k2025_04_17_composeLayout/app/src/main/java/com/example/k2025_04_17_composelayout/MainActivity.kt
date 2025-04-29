package com.example.k2025_04_17_composelayout

import android.app.Fragment
import android.os.Bundle
import android.view.View.inflate
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.example.k2025_04_17_composelayout.dataBinding
import com.example.k2025_04_17_composelayout.dataBinding.MyNewLayoutBinding
import com.example.k2025_04_17_composelayout.ui.theme.K2025_04_17_composeLayoutTheme


public var dataBinding: Boolean? = null

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

        }
    }
}

// [START android_compose_interop_apis_fragments_in_compose]
@Composable
fun myNewLayout() {
    AndroidViewBinding(MyNewLayoutBinding::inflate) {
        val myFragment = fragmentContainerView.getFragment<MyNewLayout>()
    }
}

    // [START_EXCLUDE silent]
    class MyNewLayout : Fragment()
// [END_EXCLUDE]
// [END android_compose_interop_apis_fragments_in_compose]


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
    K2025_04_17_composeLayoutTheme {
        Greeting("Android")
    }
}
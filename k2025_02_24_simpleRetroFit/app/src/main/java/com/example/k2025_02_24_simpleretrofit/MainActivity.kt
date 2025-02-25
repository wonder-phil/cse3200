package com.example.k2025_02_24_simpleretrofit

import android.os.Bundle
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
import com.example.k2025_02_24_simpleretrofit.ui.theme.K2025_02_24_simpleRetroFitTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3003/user/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

      CoroutineScope(Dispatchers.IO).launch {
          try {
              println("PGB:  HERE")
              val user = apiService.getUsers()
              println("PGB: " + user.name + " " + user.number.toString())
          } catch (e: Exception) {
              println("PGB Error: $e")
          }
        }



        setContent {
            K2025_02_24_simpleRetroFitTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Greeting(
                        name = "Android",
                        //apiService,
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}

fun getUserData(apiService: ApiService) {

    /*
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val users = apiService.getUsers()
            println("PGB: " + users.toString())
        } catch (e: Exception) {
            // Handle error
        }
    }

     */
}

@Composable
fun Greeting(name: String, //apiService: ApiService,
 modifier: Modifier = Modifier) {
       //      getUserData(apiService)
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

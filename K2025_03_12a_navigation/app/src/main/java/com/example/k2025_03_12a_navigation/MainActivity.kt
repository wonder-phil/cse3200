package com.example.k2025_03_12a_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.k2025_03_12a_navigation.ui.theme.K2025_03_12a_navigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            K2025_03_12a_navigationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    myNavigationApp(modifier = Modifier.padding(innerPadding).fillMaxSize(0.9f))

                }
            }
        }
    }

    @Composable
    fun myNavigationApp(modifier: Modifier) {
        val navController = rememberNavController()
        NavHost(navController, startDestination = "MainPage") {
            composable(route = "MainPage") {
                MainPage()
            }
            composable(route = "Page_1") {
                Page_1()
            }
        }
    }

    @Composable
    fun MainPage() {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            Text(text = "Top of column")
            Text(text = "Middle of column")
            Text(text = "Bottom of column")
        }
    }

    @Composable
    fun Page_1() {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            Text(text = "PAGE_1: Top of column")
            Text(text = "PAGE_1: Middle of column")
            Text(text = "PAGE_1: Bottom of column")
        }
    }

}
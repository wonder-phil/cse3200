package com.example.k2025_03_12b_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.k2025_03_12b_navigation.ui.theme.K2025_03_12b_navigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            K2025_03_12b_navigationTheme {
                MyApp()
            }
        }
    }


    @Serializable
    data class Profile(val name: String)

    @Serializable
    object FriendsList

    // Define the ProfileScreen composable.
    @Composable
    fun ProfileScreen(
        profile: Profile,
        onNavigateToFriendsList: () -> Unit,
    ) {
        Text("Profile for ${profile.name}")
        Button(onClick = { onNavigateToFriendsList() }) {
            Text("Go to Friends List")
        }
    }

    // Define the FriendsListScreen composable.
    @Composable
    fun FriendsListScreen(onNavigateToProfile: () -> Unit) {
        Text("Friends List")
        Button(onClick = { onNavigateToProfile() }) {
            Text("Go to Profile")
        }
    }

    // Define the MyApp composable, including the `NavController` and `NavHost`.
    @Composable
    fun MyApp() {
        val navController = rememberNavController()
        NavHost(navController, startDestination = Profile(name = "John Smith")) {
            composable<Profile> { backStackEntry ->
                val profile: Profile = backStackEntry.toRoute()
                ProfileScreen(
                    profile = profile,
                    onNavigateToFriendsList = {
                        navController.navigate(route = FriendsList)
                    }
                )
            }
            composable<FriendsList> {
                FriendsListScreen(
                    onNavigateToProfile = {
                        navController.navigate(
                            route = Profile(name = "Aisha Devi")
                        )
                    }
                )
            }
        }
    }
}


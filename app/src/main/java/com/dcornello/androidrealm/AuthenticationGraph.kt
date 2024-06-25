package com.dcornello.androidrealm
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun AuthenticationGraph(navController: NavHostController) {
    //val loginScreenViewModel = MarioLoginScreenViewModel()
    val loginScreenViewModel: MarioLoginScreenViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            MarioLoginDestination(navHostController = navController, viewModel = loginScreenViewModel)
        }
        composable(
            "page1/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
            ) { backStackEntry ->
            Scaffold {
                Column(modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "PAGE 1")
                    Text(text = backStackEntry.arguments?.getString("email") ?: "not found")
                }
            }
        }
        //composable("profile") { Profile( /* ... */ ) }
        //composable("friendslist") { FriendsList( /* ... */ ) }
        // Add more destinations similarly.
    }
}
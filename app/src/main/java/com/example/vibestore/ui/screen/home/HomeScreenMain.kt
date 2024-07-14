package com.example.vibestore.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vibestore.ui.component.BottomNavigation
import com.example.vibestore.ui.navigation.graph.HomeNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreenMain(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            BottomNavigation(
                navController = navController,
                modifier = Modifier
                    .navigationBarsPadding()
            )
        }
    ) {
        HomeNavGraph(navController = navController)
    }
}
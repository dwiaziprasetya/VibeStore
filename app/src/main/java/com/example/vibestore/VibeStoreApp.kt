package com.example.vibestore

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vibestore.ui.component.BottomNavigation
import com.example.vibestore.ui.navigation.Screen
import com.example.vibestore.ui.screen.coupon.CouponScreen
import com.example.vibestore.ui.screen.favourite.FavouriteScreen
import com.example.vibestore.ui.screen.home.HomeScreen
import com.example.vibestore.ui.screen.mycart.MyCartScreen
import com.example.vibestore.ui.screen.profile.ProfileScreen
import com.example.vibestore.ui.theme.VibeStoreTheme

@Composable
fun VibeStoreApp(
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = { BottomNavigation(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding),
            enterTransition = { fadeIn(tween(700)) },
            exitTransition = { fadeOut(tween(700)) }
        ) {
            composable(
                route = Screen.Home.route,
            ){
                HomeScreen()
            }
            composable(Screen.MyCart.route){
                MyCartScreen()
            }
            composable(Screen.Coupon.route){
                CouponScreen()
            }
            composable(Screen.Favourite.route){
                FavouriteScreen()
            }
            composable(Screen.Profile.route){
                ProfileScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun VibeStoreAppPreview() {
    VibeStoreTheme {
        VibeStoreApp()
    }
}
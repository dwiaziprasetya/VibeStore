package com.example.vibestore.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vibestore.R
import com.example.vibestore.ui.navigation.NavigationItem
import com.example.vibestore.ui.navigation.Screen
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@Composable
fun BottomNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = Color.White,
        modifier = modifier
            .height(75.dp)
            .drawWithContent {
                drawContent()
                drawLine(
                    color = Color(0xFFE3E3E3),
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 2f
                )
            }
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = "Home",
                icon = painterResource(R.drawable.homeoutlined),
                iconActive = painterResource(R.drawable.homefilled),
                screen = Screen.Home
            ),
            NavigationItem(
                title = "Product",
                icon = painterResource(R.drawable.shoppingbagoutlined),
                iconActive = painterResource(R.drawable.shoppingbagfilled),
                screen = Screen.OurProduct
            ),
            NavigationItem(
                title = "Coupon",
                icon = painterResource(R.drawable.couponoutlined),
                iconActive = painterResource(R.drawable.couponfilled),
                screen = Screen.Coupon
            ),
            NavigationItem(
                title = "Favourite",
                icon = painterResource(R.drawable.favouriteoutlined),
                iconActive = painterResource(R.drawable.favouritefilled),
                screen = Screen.Favourite
            ),
            NavigationItem(
                title = "Profile",
                icon = painterResource(R.drawable.profileoutlined),
                iconActive = painterResource(R.drawable.profilefilled),

                screen = Screen.Profile
            ),
        )
        navigationItems.map { item ->
            val isSelected = currentRoute == item.screen.route
            NavigationBarItem(
                selected = isSelected,
                icon = {
                    if (isSelected) {
                        Icon(
                            painter = item.iconActive,
                            contentDescription = item.title,
                            tint = Color("#29bf12".toColorInt())
                        )
                    } else {
                        Icon(
                            painter = item.icon,
                            contentDescription = item.title,
                            tint = MaterialTheme.colorScheme.outline
                        )
                    }
                },
                label = {
                    if (isSelected) {
                        Text(
                            text = item.title,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Medium,
                            color = Color("#29bf12".toColorInt())
                        )
                    } else {
                        Text(
                            text = item.title,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White
                ),
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
private fun BottomNavigationPreview() {
    VibeStoreTheme {
        BottomNavigation(rememberNavController())
    }
}
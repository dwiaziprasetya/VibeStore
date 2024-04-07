package com.example.vibestore.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vibestore.R
import com.example.vibestore.model.BottomBarItem
import com.example.vibestore.ui.theme.VibeStoreTheme

@Composable
fun BottomNavigation(
) {
    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier
            .height(75.dp)
    ) {
        val navigationItems = listOf(
            BottomBarItem(
                title = "Home",
                icon = painterResource(R.drawable.homeoutlined),
            ),
            BottomBarItem(
                title = "Cart",
                icon = painterResource(R.drawable.cartoutlined),
            ),
            BottomBarItem(
                title = "Coupon",
                icon = painterResource(R.drawable.couponoutlined),
            ),
            BottomBarItem(
                title = "Favourite",
                icon = painterResource(R.drawable.favouriteoutlined),
            ),
            BottomBarItem(
                title = "Profile",
                icon = painterResource(R.drawable.profileoutlined),
            ),
        )
        navigationItems.map {
            NavigationBarItem(
                selected = it.title == navigationItems[0].title,
                onClick = {
                },
                icon = {
                    Icon(
                        painter = it.icon,
                        contentDescription = it.title,
                        tint = MaterialTheme.colorScheme.outline
                    )
                },
                label = {
                    Text(
                        text = it.title,
                        color = MaterialTheme.colorScheme.outline
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White
                )
            )
        }
    }
}

@Preview
@Composable
private fun BottomNavigationPreview() {
    VibeStoreTheme {
        BottomNavigation()
    }
}
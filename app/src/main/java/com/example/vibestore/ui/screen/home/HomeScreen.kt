package com.example.vibestore.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vibestore.R
import com.example.vibestore.ui.component.HomeSection
import com.example.vibestore.ui.component.ImageSlider
import com.example.vibestore.ui.component.TabCategory
import com.example.vibestore.ui.navigation.Screen
import com.example.vibestore.ui.screen.foryou.ForYouScreen
import com.example.vibestore.ui.theme.VibeStoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navcontroller: NavHostController,
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = {
                    Text(
                        text = "",
                    )
                },
                navigationIcon = {
                    Icon(
                        painter = painterResource(R.drawable.hamburger_menu),
                        modifier = Modifier
                            .size(32.dp),
                        contentDescription = "Menu"
                    )
                },
                actions = {
                    Icon(
                        painter = painterResource(R.drawable.cartoutlined),
                        contentDescription = "Cart"
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(
                        painter = painterResource(R.drawable.notificationoutlined),
                        contentDescription = null
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    scrolledContainerColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            HomeSection(
                title = "",
                content = { ImageSlider() },
                navigateToSeeAll = {}
            )
            HomeSection(
                title = "Categories",
                content = {
                    TabCategory(
                        gridHeight = 548.dp,
                        limit = 4,
                        navigateToDetail = { productId ->
                            navcontroller.navigate(Screen.DetailProduct.createRoute(productId))
                        },
                    )
                },
                navigateToSeeAll = {
                    navcontroller.navigate(Screen.Categories.route)
                }
            )
            HomeSection(
                title = "For You",
                content = {
                    ForYouScreen(
                        navigateToDetail = { productId ->
                            navcontroller.navigate(Screen.DetailProduct.createRoute(productId))
                        }
                    )
                },
                navigateToSeeAll = {}
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    VibeStoreTheme {
        HomeScreen(navcontroller = rememberNavController())
    }
}
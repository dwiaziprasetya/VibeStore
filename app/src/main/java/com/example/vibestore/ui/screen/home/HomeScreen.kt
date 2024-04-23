package com.example.vibestore.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.vibestore.R
import com.example.vibestore.ui.component.HomeSection
import com.example.vibestore.ui.component.ImageSlider
import com.example.vibestore.ui.component.TabCategory
import com.example.vibestore.ui.screen.foryou.ForYouScreen
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToSeeAll: () -> Unit,
    navigateToDetail: (Int) -> Unit
) {
    Scaffold (
        modifier = Modifier,
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = {
                    Text(
                        text = "Vibe Store",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        color = Color("#29bf12".toColorInt()),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menu"
                    )
                },
                actions = {
                    Icon(
                        painter = painterResource(R.drawable.cartoutlined),
                        contentDescription = "Cart"
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
                          navigateToDetail = navigateToDetail
                      )
                },
                navigateToSeeAll = navigateToSeeAll
            )
            HomeSection(
                title = "For You",
                content = {
                    ForYouScreen(
                        navigateToDetail = navigateToDetail
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
        HomeScreen(navigateToDetail = {}, navigateToSeeAll = {})
    }
}
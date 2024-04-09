package com.example.vibestore.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vibestore.ui.component.HomeSection
import com.example.vibestore.ui.component.ImageSlider
import com.example.vibestore.ui.component.Search
import com.example.vibestore.ui.component.TabCategory
import com.example.vibestore.ui.screen.foryou.ForYou
import com.example.vibestore.ui.theme.VibeStoreTheme

@Composable
fun HomeScreen(
    navigateToMyProduct: () -> Unit
) {
    Scaffold (
        topBar = {
            Search()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            HomeSection(
                title = "Best Seller",
                content = { ImageSlider() },
                navigateToMyProduct = {}
            )
            HomeSection(
                title = "Categories",
                content = { TabCategory() },
                navigateToMyProduct = navigateToMyProduct
            )
            HomeSection(
                title = "For You",
                content = { ForYou() },
                navigateToMyProduct = {

                }
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    VibeStoreTheme {
    }
}
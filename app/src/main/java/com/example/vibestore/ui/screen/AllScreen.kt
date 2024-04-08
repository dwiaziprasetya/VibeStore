package com.example.vibestore.ui.screen

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.vibestore.ui.component.ProductCard
import com.example.vibestore.ui.theme.VibeStoreTheme

@Composable
fun AllScreen() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
    ) {
        items(4){
            ProductCard()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AllScreenPreview() {
    VibeStoreTheme {
        AllScreen()
    }
}
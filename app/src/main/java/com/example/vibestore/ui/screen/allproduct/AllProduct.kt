package com.example.vibestore.ui.screen.allproduct

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vibestore.ui.component.ProductCard
import com.example.vibestore.ui.theme.VibeStoreTheme

@Composable
fun AllScreen() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .height(525.dp)
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
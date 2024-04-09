package com.example.vibestore.ui.screen.foryou

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.vibestore.R
import com.example.vibestore.ui.component.ProductCard
import com.example.vibestore.ui.theme.VibeStoreTheme

@Composable
fun ForYou(image: Int) {
    LazyRow{
        items(5){
            ProductCard(image)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ForYouPreview() {
    VibeStoreTheme {
        ForYou(R.drawable.samplemodel2)
    }
}

@Preview(showBackground = true)
@Composable
private fun ForYouPreview2() {
    VibeStoreTheme {
        ForYou(R.drawable.samplemodel1)
    }
}
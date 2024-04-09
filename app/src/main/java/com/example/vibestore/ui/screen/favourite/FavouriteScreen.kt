package com.example.vibestore.ui.screen.favourite

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.vibestore.ui.component.SectionInProgress
import com.example.vibestore.ui.theme.VibeStoreTheme

@Composable
fun FavouriteScreen() {
    SectionInProgress()
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_4_XL
)
@Composable
private fun FavouriteScreenPreview() {
    VibeStoreTheme {
        FavouriteScreen()
    }
}
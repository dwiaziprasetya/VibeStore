package com.example.vibestore.ui.screen.coupon

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.vibestore.ui.component.SectionInProgress
import com.example.vibestore.ui.theme.VibeStoreTheme

@Composable
fun CouponScreen() {
    SectionInProgress()
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_4_XL
)
@Composable
private fun CouponScreenPreview() {
    VibeStoreTheme {
        CouponScreen()
    }
}
package com.example.vibestore.ui.screen.coupon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vibestore.R
import com.example.vibestore.ui.theme.VibeStoreTheme

@Composable
fun CouponScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(70.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.errordata),
                contentDescription = "Error Data",
                modifier = Modifier.size(290.dp)
            )
            Text(
                text = "This section has not yet been finalized by the developer",
                maxLines = 2,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
        }
    }
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
package com.example.vibestore.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vibestore.R
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@Composable
fun CartItem() {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Image(
            modifier = Modifier.size(90.dp),
            painter = painterResource(R.drawable.clothes),
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = "Crewneck Sweatshirt",
                fontFamily = poppinsFontFamily,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Category : Men",
                fontFamily = poppinsFontFamily,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.outline
            )

            Text(
                text = "$56.50",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold
            )
        }
        ProductCounter(modifier = Modifier.align(Alignment.CenterVertically))
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
private fun CartItemPreview() {
    VibeStoreTheme {
        CartItem()
    }
}
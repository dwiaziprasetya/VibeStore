package com.example.vibestore.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@Composable
fun ProductCard(
    image: String,
    title: String,
    price: Any
) {
    Column(
        modifier = Modifier
            .width(196.dp)
            .padding(16.dp)
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(196.dp)
                .height(196.dp)
                .padding(bottom = 8.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Text(
            text = title,
            maxLines = 1,
            fontSize = 12.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
        )
        Text(
            text = "$$price",
            fontSize = 14.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductCardPreview() {
    VibeStoreTheme {
        ProductCard(
            image = "https://cdn.rri.co.id/berita/1/images/1689391542821-images_(22)/1689391542821-images_(22).jpeg",
            title = "Nasi Padang",
            price = 184
        )
    }
}
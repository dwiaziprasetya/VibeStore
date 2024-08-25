package com.example.vibestore.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.vibestore.R
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@Composable
fun FavouriteItem(
    modifier: Modifier = Modifier,
    imageId: String,
    productName: String,
    category: String,
    price: String,
) {
    Row(
        modifier = modifier
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            )
            .height(100.dp)
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.background
            )
    ) {
        AsyncImage(
            modifier = Modifier.size(
                height = 100.dp,
                width = 100.dp
            ),
            model = imageId,
            contentDescription = null
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
                .weight(1f)
        ) {
            Text(
                text = productName,
                fontFamily = poppinsFontFamily,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 2
            )
            Text(
                text = "Category : $category",
                fontFamily = poppinsFontFamily,
                fontSize = 12.sp,
                maxLines = 1,
                color = MaterialTheme.colorScheme.outline
            )
            Spacer(modifier = Modifier.padding(top = 8.dp))
            Text(
                text = "$$price",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium
            )
        }
        Icon(
            painter = painterResource(R.drawable.icon_favourite_filled_red),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .padding(
                    end = 8.dp,
                    bottom = 4.dp,

                )
                .align(Alignment.Bottom)
        )
    }
}

@Preview
@Composable
private fun FavouriteItemPreview() {
    VibeStoreTheme {
        FavouriteItem(
            imageId = "",
            productName = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            category = "men's clothing",
            price = "78"
        )
    }
}
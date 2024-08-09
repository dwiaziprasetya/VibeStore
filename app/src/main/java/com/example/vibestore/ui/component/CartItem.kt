package com.example.vibestore.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    imageId: String,
    productName: String,
    category: String,
    price: String,
    orderCount: Int,
    onQuantityChange: (Int) -> Unit,
) {
    val totalPrice = price.toDouble() * orderCount

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
                text = "$${"%.2f".format(totalPrice)}",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium
            )
        }
        ProductCounter(
            modifier = Modifier.align(Alignment.CenterVertically),
            orderCount = orderCount,
            onIncrement = { onQuantityChange(orderCount + 1) },
            onDecrement = { onQuantityChange(orderCount - 1) }
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
private fun CartItemPreview() {
    VibeStoreTheme {
        CartItem(
            imageId = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            productName = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            category = "men's clothing",
            price = "78",
            orderCount = 2,
            onQuantityChange = {}
        )
    }
}
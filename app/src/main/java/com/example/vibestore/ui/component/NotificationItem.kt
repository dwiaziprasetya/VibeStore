package com.example.vibestore.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
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
import com.example.vibestore.R
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@Composable
fun NotificationItem(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .padding(
                vertical = 16.dp,
                horizontal = 16.dp
            )
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(R.drawable.icon_cart_filled),
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.size(10.dp))
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Shopping",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.outline,
                    fontWeight = FontWeight.Light,
                    fontFamily = poppinsFontFamily,
                    modifier = Modifier
                        .weight(1f)
                )
                Text(
                    text = "20 Agu",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.outline,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Light
                )
            }
            Text(
                text = "Your order has been shipped",
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                fontFamily = poppinsFontFamily
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .background(
                        color = Color(0xFFE3E3E3),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(6.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.image_clothes),
                    contentDescription = "",
                    modifier = Modifier.size(50.dp)
                )
                Spacer(modifier = Modifier.size(10.dp))
                Column {
                    Text(
                        text = "Mens Casual Premium Slim Fit T-Shirts",
                        maxLines = 1,
                        fontSize = 16.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "and 3 others",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.primary,
                        fontFamily = poppinsFontFamily,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NotificationItemPreview() {
    VibeStoreTheme(dynamicColor = false
    ) {
        NotificationItem()
    }
}
package com.example.vibestore.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
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
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import com.example.vibestore.R
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@Composable
fun NotificationItem(
    notificationType: String,
    date: String,
    message: String,
    messageDetail: String = "",
    firstProductImage: String = "",
    firstProductName: String = "",
    quantityCheckout: Int = 0,
    isRead: Boolean,
    onNotificationClick: () -> Unit
) {
    val shortDate = date.split(" ").let {
        if (it.size >= 2) "${it[0]} ${it[1].take(3)}" else date
    }

    Row(
        modifier = Modifier
            .background(
                color = if (isRead) MaterialTheme.colorScheme.background
                else Color("#b9ffaf".toColorInt()),
            )
            .padding(
                vertical = 16.dp,
                horizontal = 16.dp
            )
            .clickable { onNotificationClick() }
            .fillMaxWidth()
    ) {
        if (notificationType == "Shopping") {
            Icon(
                painter = painterResource(R.drawable.icon_shopping_bag_filled),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        } else {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "",
                tint = Color("#0096c7".toColorInt())
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = notificationType,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = poppinsFontFamily,
                    modifier = Modifier
                        .weight(1f)
                )
                Text(
                    text = shortDate,
                    fontSize = 14.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Light
                )
            }
            Text(
                text = message,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                fontFamily = poppinsFontFamily
            )
            if (notificationType == "Shopping") {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
//                    .background(
//                        color = Color(0xFFE3E3E3),
//                        shape = RoundedCornerShape(10.dp)
//                    )
                        .fillMaxWidth()
                        .padding(6.dp)
                ) {
                    AsyncImage(
                        model = firstProductImage,
                        contentDescription = "",
                        modifier = Modifier.size(50.dp)
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Column {
                        Text(
                            text = firstProductName,
                            maxLines = 1,
                            fontSize = 16.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Normal
                        )
                        if (quantityCheckout > 1) {
                            Text(
                                text = "and $quantityCheckout others",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.primary,
                                fontFamily = poppinsFontFamily,
                            )
                        }
                    }
                }
            } else {
                Text(
                    text = messageDetail,
                    maxLines = 3,
                    fontSize = 14.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun NotificationItemPreview() {
    VibeStoreTheme(dynamicColor = false
    ) {
        NotificationItem(
            notificationType = "Shopping",
            date = "20 Agustus 2023",
            message = "Your order has been shipped",
            firstProductImage = "",
            firstProductName = "Men's Casual Slim Fit Shirt",
            quantityCheckout = 2,
            isRead = false,
            onNotificationClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NotificationItemPreview2() {
    VibeStoreTheme(dynamicColor = false
    ) {
        NotificationItem(
            notificationType = "Info",
            date = "20 Agustus 2023",
            message = "Welcome to Vibe Store 🙌",
            messageDetail = "We’re thrilled to have you on board! Explore amazing deals and start shopping now.",
            isRead = false,
            onNotificationClick = {}
        )
    }
}
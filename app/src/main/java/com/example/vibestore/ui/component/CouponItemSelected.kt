package com.example.vibestore.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@Composable
fun CouponItemSelected(
    modifier: Modifier = Modifier,
    discountTittle: String
) {
    Card(
        border = BorderStroke(
            width = 1.dp,
            color = Color.LightGray
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .height(55.dp)
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = discountTittle,
                fontSize = 16.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 13.dp)
            ) {
                Text(
                    text = "Coupon applied",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = poppinsFontFamily,
                )
                Spacer(modifier = Modifier.size(4.dp))
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .offset(y = (-2).dp)
                        .size(20.dp)
                )
            }
        }
    }
}

@Composable
fun CouponInactiveSelected(modifier: Modifier = Modifier) {
    Card(
        border = BorderStroke(
            width = 1.dp,
            color = Color.LightGray
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
            .padding(top = 8.dp)
            .height(55.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            Text(
                text = "Apply Coupon",
                fontSize = 14.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CouponInactiveSelectedPreview() {
    VibeStoreTheme(dynamicColor = false) {
        CouponInactiveSelected()
    }
}

@Preview(showBackground = true)
@Composable
private fun CouponItemPreview() {
    VibeStoreTheme(dynamicColor = false) {
        CouponItemSelected(
            discountTittle = "FREE SHIPPING"
        )
    }
}
package com.example.vibestore.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@Composable
fun CouponCard(
    modifier: Modifier = Modifier,
    discount: String,
    description: String,
    expiredDate: String,
    color1: Color,
    fontSizeDiscount: Int = 40,
    fontSizeExpiredDate: Int = 12,
    fontSizeDescription: Int = 12,
    height: Dp = 200.dp,
    color2: Color
) {
    val gradient = Brush.linearGradient(
        colors = listOf(
            color1,
            color2
        )
    )

    Card(
        modifier = modifier
            .height(height)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradient)
        ) {
            Text(
                fontSize = fontSizeDiscount.sp,
                text = discount,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                modifier = Modifier
                    .padding(16.dp),
            )
            Column(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 8.dp
                    )
                    .align(Alignment.BottomStart)
            ) {
                Text(
                    text = description,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                    fontSize = fontSizeDescription.sp
                )
                Text(
                    text = "Valid until $expiredDate",
                    fontWeight = FontWeight.Light,
                    fontFamily = poppinsFontFamily,
                    color = Color.White,
                    fontSize = fontSizeExpiredDate.sp
                )
            }
            Icon(
                imageVector = Icons.Default.Circle,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.CenterStart)
                    .offset(x = (-10).dp)
            )
            Icon(
                imageVector = Icons.Default.Circle,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.CenterEnd)
                    .offset(x = 10.dp)
            )
            Canvas(modifier = Modifier.fillMaxSize()) {
                val dashWidth = 7.dp.toPx()
                val dashGap = 1.dp.toPx()

                val y = size.height / 2

                var currentX = 0f
                while (currentX < size.width) {
                    val endX = (currentX + dashWidth).coerceAtMost(size.width)
                    drawLine(
                        color = Color.White,
                        start = Offset(currentX + 9, y),
                        end = Offset(endX, y),
                        strokeWidth = 2.dp.toPx()
                    )
                    currentX += dashWidth + dashGap
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun CouponCardPreview() {
    VibeStoreTheme {
        CouponCard(
            discount = "25%",
            description = "Applies to get 25% off",
            expiredDate = "31 Desember 2024",
            color1 = Color(0xFFDA22FF),
            color2 = Color(0xFF9733EE)
        )
    }
}
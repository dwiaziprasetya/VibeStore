package com.example.vibestore.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    fontSizeExpiredDate: Int = 14,
    fontSizeDescription: Int = 14,
    height: Dp = 170.dp,
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

@Composable
fun CouponCard2(
    modifier: Modifier = Modifier,
    discount: String,
    description: String,
    expiredDate: String,
    couponCode: String,
) {

    Card(
        elevation = CardDefaults.elevatedCardElevation(
            focusedElevation = 6.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(80.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary
                    )
                    .align(Alignment.CenterStart),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = discount,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = if (discount == "FREE SHIPPING") 16.sp
                    else 30.sp,
                    modifier = Modifier.rotate(270f)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(
                        start = 90.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 16.dp
                    )
                    .align(Alignment.CenterStart)
            ) {
                Text(
                    text = couponCode,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    color = Color(0xFF4CAF50),  // Warna hijau untuk highlight
                    fontSize = 14.sp,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = expiredDate,
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CouponCardPreview2() {
    VibeStoreTheme(
        dynamicColor = false
    ) {
        CouponCard2(
            modifier = Modifier.padding(16.dp),
            discount = "FREE SHIPPING",
            description = "Applies to get 25% off",
            expiredDate = "31 Desember 2024",
            couponCode = "ADERTS4TA"
        )
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
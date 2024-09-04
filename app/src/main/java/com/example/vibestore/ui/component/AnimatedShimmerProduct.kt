package com.example.vibestore.ui.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@Composable
fun AnimatedShimmerProduct() {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            repeatMode = RepeatMode.Reverse,
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(
            x = translateAnimation.value,
            y = translateAnimation.value
        )
    )

    ShimmerGridItem(brush = brush)
}

@Composable
fun ShimmerGridItem(brush: Brush) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .wrapContentHeight()
            .padding(16.dp),
    ) {
        AsyncImage(
            model = "",
            contentDescription = "model2",
            modifier = Modifier
                .padding(bottom = 16.dp)
                .height(160.dp)
                .clip(RoundedCornerShape(8.dp))
                .width(160.dp)
                .background(brush)
        )
        Text(
            text = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t",
            modifier = Modifier
                .background(
                    brush = brush,
                    shape = RoundedCornerShape(8.dp)
                ),
            maxLines = 1,
            fontSize = 10.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
        )
        Text(
            text = "\t\t\t\t\t\t\t" +
                    "\t\t\t\t\t\t\t" +
                    "\t\t\t\t\t\t\t" +
                    "\t\t\t\t\t\t\t",
            modifier = Modifier
                .padding(top = 8.dp)
                .background(
                    brush = brush,
                    shape = RoundedCornerShape(8.dp)
                ),
            maxLines = 1,
            fontSize = 10.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
        )
        Text(
            text = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t",
            modifier = Modifier
                .padding(top = 16.dp)
                .background(
                    brush = brush,
                    shape = RoundedCornerShape(8.dp)
                ),
            maxLines = 1,
            fontSize = 10.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Medium,
        )
        Row {
            Text(
                text = "\t\t\t\t\t\t",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .background(
                        brush = brush,
                        shape = RoundedCornerShape(8.dp)
                    ),
                maxLines = 1,
                fontSize = 10.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "\t\t\t\t\t\t",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .background(
                        brush = brush,
                        shape = RoundedCornerShape(8.dp)
                    ),
                maxLines = 1,
                fontSize = 10.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AnimatedShimmerPreview() {
    VibeStoreTheme {
        AnimatedShimmerProduct()
    }
}
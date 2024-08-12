package com.example.vibestore.ui.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun AnimatedShimmerDetailProduct(modifier: Modifier = Modifier) {
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

    ShimmerItem(brush = brush, modifier = modifier)
}

@Composable
fun ShimmerItem(
    brush: Brush,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = "",
                contentDescription = null,
                modifier = Modifier
                    .background(brush)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .height(300.dp)
            )
            Text(
                text = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .background(
                        brush = brush,
                        shape = RoundedCornerShape(8.dp)
                    ),
                maxLines = 2,
                fontSize = 22.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
            )
            Text(
                text = "\t\t\t\t\t\t\t\t",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .background(
                        brush = brush,
                        shape = RoundedCornerShape(8.dp)
                    ),
                maxLines = 2,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
            )
            Text(
                text = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t",
                modifier = Modifier
                    .padding(top = 32.dp)
                    .background(
                        brush = brush,
                        shape = RoundedCornerShape(8.dp)
                    ),
                maxLines = 1,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
            )
            Text(
                text = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                        "\t\t\t\t\t\t\t\t\t\t\t\t\t\t",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .background(
                        brush = brush,
                        shape = RoundedCornerShape(8.dp)
                    ),
                maxLines = 1,
                fontSize = (13.5).sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Medium,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(85.dp)
                .background(Color.White)
        ) {
            Divider()
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier
                        .background(
                            shape = RoundedCornerShape(8.dp),
                            brush = brush
                        ),
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 40.sp,
                    text = "\t\t\t\t\t\t\t\t\t\t\t\t\t"
                )
                Spacer(modifier = Modifier.weight(1f))
                Surface(
                    modifier = Modifier
                        .height(55.dp)
                        .width(170.dp)
                        .clip(RoundedCornerShape(8.dp)),
                ) {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .background(brush)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AnimatedShimmerDetailProductPreview() {
    VibeStoreTheme {
        AnimatedShimmerDetailProduct()
    }
}
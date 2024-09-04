package com.example.vibestore.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.vibestore.model.dummySliderImage
import com.example.vibestore.ui.theme.VibeStoreTheme
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider() {
    val pagerState = rememberPagerState(pageCount = {
        dummySliderImage.size
    })
    Column {
        HorizontalPager(
            contentPadding = PaddingValues(horizontal = 16.dp),
            pageSpacing = 16.dp,
            state = pagerState,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 16.dp)
        ) { page ->
            Image(
                painter = painterResource(dummySliderImage[page].image),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
            )
        }
//        Row(
//            modifier = Modifier
//                .wrapContentHeight()
//                .fillMaxWidth()
//                .padding(bottom = 8.dp),
//            horizontalArrangement = Arrangement.Center
//        ) {
//            repeat(pagerState.pageCount) { iteration ->
//                val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
//                Box(
//                    modifier = Modifier
//                        .padding(2.dp)
//                        .clip(CircleShape)
//                        .background(color)
//                        .size(8.dp)
//                )
//            }
//        }
        PageIndicator(
            numberOfPages = pagerState.pageCount,
            selectedPage = pagerState.currentPage,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp)
        )
    }

    // Auto Slide
    LaunchedEffect(Unit) {
        while(true) {
            delay(4000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.animateScrollToPage(nextPage)
        }
    }
}

@Composable
fun PageIndicator(
    numberOfPages: Int,
    modifier: Modifier = Modifier,
    selectedPage: Int = 0,
    selectedColor: Color = MaterialTheme.colorScheme.primary,
    defaultColor: Color = Color.LightGray,
    defaultRadius: Dp = 10.dp,
    selectedRadius: Dp = 25.dp,
    space: Dp = 8.dp,
    animationDurationMillis: Int = 400
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space),
        modifier = modifier
    ) {
        for (i in 0 until numberOfPages) {
            val isSelected = i == selectedPage
            PagerIndicatorContent(
                isSelected = isSelected,
                selectedColor = selectedColor,
                defaultColor = defaultColor,
                defaultRadius = defaultRadius,
                selectedRadius = selectedRadius,
                animationDurationMillis = animationDurationMillis
            )
        }
    }
}

@Composable
fun PagerIndicatorContent(
    isSelected:Boolean,
    selectedColor: Color,
    defaultColor: Color,
    defaultRadius: Dp,
    selectedRadius: Dp,
    animationDurationMillis: Int,
    modifier: Modifier = Modifier
) {
    val color: Color by animateColorAsState(
        targetValue = if (isSelected) {
            selectedColor
        } else {
            defaultColor
        },
        animationSpec = tween(
            durationMillis = animationDurationMillis
        ), label = ""
    )

    val width: Dp by animateDpAsState(
        targetValue = if (isSelected) {
            selectedRadius
        } else {
            defaultRadius
        },
        animationSpec = tween(
            durationMillis = animationDurationMillis
        ), label = ""
    )

    Canvas(
        modifier = modifier
            .size(
                width = width,
                height = defaultRadius
            )
    ) {
        drawRoundRect(
            color = color,
            topLeft = Offset.Zero,
            size = Size(
                width = width.toPx(),
                height = defaultRadius.toPx(),
            ),
            cornerRadius = CornerRadius(
                x = defaultRadius.toPx(),
                y = defaultRadius.toPx()
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PageIndicatorPreview() {
    VibeStoreTheme(dynamicColor = false) {
        PageIndicator(
            numberOfPages = 3,
            selectedPage = 0
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ImageSliderPreview() {
    VibeStoreTheme(
        dynamicColor = false
    ) {
        ImageSlider()
    }
}
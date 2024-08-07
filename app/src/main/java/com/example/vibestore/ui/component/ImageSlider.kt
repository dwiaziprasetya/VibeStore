package com.example.vibestore.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }
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


@Preview(showBackground = true)
@Composable
private fun ImageSliderPreview() {
    VibeStoreTheme {
        ImageSlider()
    }
}
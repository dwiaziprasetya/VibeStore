package com.example.vibestore.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vibestore.model.dummySliderImage
import com.example.vibestore.ui.theme.VibeStoreTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider() {
    val pagerState = rememberPagerState(pageCount = {
        dummySliderImage.size
    })
    HorizontalPager(
        contentPadding = PaddingValues(start = 16.dp, end = 100.dp),
        pageSpacing = 16.dp,
        state = pagerState,
        modifier = Modifier
            .padding(
                top = 16.dp,
                bottom = 16.dp
            )
    ) { page ->
        Image(
            painter = painterResource(dummySliderImage[page].image),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun ImageSliderPreview() {
    VibeStoreTheme {
        ImageSlider()
    }
}
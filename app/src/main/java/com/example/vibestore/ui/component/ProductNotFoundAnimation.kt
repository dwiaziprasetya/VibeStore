package com.example.vibestore.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.vibestore.R
import com.example.vibestore.ui.theme.VibeStoreTheme

@Composable
fun ProductNotFoundAnimation(modifier: Modifier = Modifier) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.product_not_found_animation_lottie
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier
            .size(300.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun ProductNotFoundAnimationPreview() {
    VibeStoreTheme(dynamicColor = false) {
        Box(modifier = Modifier.fillMaxSize()){
            ProductNotFoundAnimation(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}
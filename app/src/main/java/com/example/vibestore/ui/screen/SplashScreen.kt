package com.example.vibestore.ui.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vibestore.R
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.ui.navigation.Screen
import com.example.vibestore.ui.screen.main.MainViewModel
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            context = LocalContext.current
        )
    )
) {
    val verticalOffset = remember { Animatable(-300f) }  // Start above the screen
    val alpha = remember { Animatable(0f) }  // Start invisible
    val scope = rememberCoroutineScope()
    var showProgressBar by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        scope.launch {
            launch {
                verticalOffset.animateTo(
                    targetValue = 0f, // Move to the original position
                    animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing)
                )
            }
            launch {
                alpha.animateTo(
                    targetValue = 1f, // Fade in
                    animationSpec = tween(durationMillis = 1000)
                )
            }
            delay(2000) // Delay for 1000 milliseconds
            showProgressBar = true
        }
        delay(3000) // Delay for 3000 milliseconds
        viewModel.getSession().collect { session ->
            val startDestination = if (session.token.isNotEmpty()) {
                Screen.MainNav.route
            } else {
                Screen.AuthNav.route
            }
            navController.navigate(startDestination) {
                popUpTo(Screen.Splash.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset { IntOffset(0, verticalOffset.value.roundToInt()) }
                .alpha(alpha.value),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.fui),
                    contentDescription = null,
                    modifier = Modifier
                        .size(96.dp)
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Text(
                        text = "VIBE STORE",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color("#29bf12".toColorInt())
                    )
                    Text(
                        text = "choose your own",
                        fontFamily = poppinsFontFamily,
                        fontSize = 20.sp,
                        color = Color("#29bf12".toColorInt())
                    )
                }
            }

            if (showProgressBar) {
                CircularProgressIndicator(
                    color = Color("#29bf12".toColorInt()),
                    modifier = Modifier
                        .padding(top = 32.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenPreview() {
    VibeStoreTheme {
        SplashScreen(
            rememberNavController()
        )
    }
}

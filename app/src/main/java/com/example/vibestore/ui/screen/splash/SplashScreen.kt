package com.example.vibestore.ui.screen.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vibestore.R
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.ui.navigation.model.Screen
import com.example.vibestore.ui.screen.main.MainViewModel
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel(
        factory = ViewModelFactory.getInstance(
            context = LocalContext.current
        )
    )
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val imageOffsetX = remember { Animatable(screenWidth.value/4) }
    val textOffsetX = remember { Animatable(screenWidth.value / 1.4f) }

    LaunchedEffect(Unit) {
        imageOffsetX.animateTo(
            targetValue = -5f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
        textOffsetX.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        )
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
        Row(
            modifier = Modifier.align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .offset(x = imageOffsetX.value.dp)
                    .size(120.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier
                    .offset(x = textOffsetX.value.dp),
                text = "VIBE STORE",
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color("#29bf12".toColorInt())
            )
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

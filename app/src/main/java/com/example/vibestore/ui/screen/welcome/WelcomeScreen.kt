package com.example.vibestore.ui.screen.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vibestore.R
import com.example.vibestore.model.dummyTextWelcome
import com.example.vibestore.ui.navigation.Screen
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(navController: NavHostController) {
    val pagerState = rememberPagerState(
        pageCount = {
            dummyTextWelcome.size
        }
    )
    Box {
        Image(
            painter = painterResource(R.drawable.aaaaaa),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(320.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStart = 40.dp,
                        topEnd = 40.dp
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .padding(
                            start = 43.dp,
                            end = 42.dp
                        )
                        .fillMaxWidth()
                ) { page ->
                    TextCustomView(
                        text1 = dummyTextWelcome[page].text1,
                        text2 = dummyTextWelcome[page].text2
                    )
                }
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .padding(
                            top = 20.dp,
                            bottom = 38.dp
                        ),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val color = if (pagerState.currentPage == iteration) Color("#29bf12".toColorInt()) else Color.LightGray
                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .clip(CircleShape)
                                .background(color)
                                .size(8.dp)
                        )
                    }
                }
                Button(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .height(55.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(40.dp),
                    onClick = {
                        navController.navigate(Screen.Home.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    )
                ) {
                    Text(
                        fontFamily = poppinsFontFamily,
                        text = "Get Started",
                        fontSize = 16.sp,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@Composable
fun TextCustomView(
    text1: String,
    text2: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            lineHeight = 30.sp,
            textAlign = TextAlign.Center,
            text = text1,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        Text(
            textAlign = TextAlign.Center,
            text = text2,
            color = androidx.compose.material3.MaterialTheme.colorScheme.outline
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TextCustomViewPreview() {
    VibeStoreTheme {
        TextCustomView(
            text1 = "Discovering your\n " +
                    "fancy fashion style",
            text2 = "Find the perfect outfit for any occasion,\n" +
                    "from casual wear to formal attire"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeScreenPreview() {
    VibeStoreTheme {
        WelcomeScreen(rememberNavController())
    }
}
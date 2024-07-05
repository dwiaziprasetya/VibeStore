@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.vibestore.ui.screen.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen(navController: NavHostController) {
    val systemUiController = rememberSystemUiController()

    val pagerState = rememberPagerState(
        pageCount = {
            dummyTextWelcome.size
        }
    )
    var isSheetOpen by rememberSaveable { mutableStateOf(false) }
    val modalBottomSheetState = rememberModalBottomSheetState()

    if (isSheetOpen) {
        BottomSheetRegister(
            onDismiss = {
                isSheetOpen = false
            },
            sheetState = modalBottomSheetState,
        )
    }

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
                        isSheetOpen = true
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetRegister(
    onDismiss: () -> Unit,
    sheetState: SheetState
) {
    val bottomPadding = WindowInsets.navigationBars.asPaddingValues()
        .calculateBottomPadding().value.toInt() + 8
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        modifier = Modifier.statusBarsPadding(),
        windowInsets = BottomSheetDefaults
            .windowInsets
            .only(
                WindowInsetsSides.Bottom
            )
    ) {
        BottomSheetWelcomeContent()
    }
}

@Composable
fun BottomSheetWelcomeContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(288.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.White)
                .align(Alignment.Center)
        ) {
            Button(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(55.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(40.dp),
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color("#29bf12".toColorInt())
                )
            ) {
                Text(
                    fontFamily = poppinsFontFamily,
                    text = "Sign Up",
                    fontSize = 16.sp,
                    modifier = Modifier
                )
            }
            Button(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 16.dp,
                        end = 16.dp
                    )
                    .height(55.dp)
                    .fillMaxWidth()
                    .border(
                        color = Color.Black,
                        width = 0.1.dp,
                        shape = RoundedCornerShape(40.dp)
                    ),
                shape = RoundedCornerShape(40.dp),
                onClick = {
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {
                Text(
                    fontFamily = poppinsFontFamily,
                    text = "Login to Vibe Store",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomSheetRegisterPreview() {
    VibeStoreTheme {
        BottomSheetWelcomeContent()
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
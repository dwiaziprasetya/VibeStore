package com.example.vibestore.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.vibestore.R
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(horizontal = 8.dp),
                title = {
                    Text(
                        text = "Product Details",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        tint = Color.Black,
                        contentDescription = "back"
                    )
                },
                actions = {
                    Icon(
                        painter = painterResource(R.drawable.favouriteoutlined),
                        contentDescription = "favourite",
                        tint = Color.Black
                    )
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                Modifier
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Image(
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 16.dp,
                        )
                        .height(400.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    painter = painterResource(R.drawable.clothes),
                    contentDescription = "model2"
                )
                Text(
                    modifier = Modifier.padding(
                        top = 16.dp,
                    ),
                    text = "Mens Casual Premium",
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp
                )
                Row (
                    modifier = Modifier.padding(top = 8.dp)
                ){
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color("#FFB000".toColorInt())
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "4.8",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = "(228)",
                        color = MaterialTheme.colorScheme.outline,
                        fontFamily = poppinsFontFamily,
                    )
                }
                Text(
                    fontSize = 12.sp,
                    text = """
                    Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.
                    Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.
                    Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.
                    Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.
                    Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.
                    Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.
                    Slim-fitting style, contrast raglan long sleeve, three-button henley placket, light weight & soft fabric for breathable and comfortable wearing. And Solid stitched shirts with round neck made for durability and a great fit for casual fashion wear and diehard baseball fans. The Henley style round neckline includes a three-button placket.
                """.trimIndent(),
                    fontFamily = poppinsFontFamily,
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(85.dp)
                    .align(Alignment.BottomCenter)
                    .background(color = Color.White)
            ) {
                Divider()
                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .align(Alignment.CenterStart)
                        .fillMaxWidth(),
                ) {
                    Text(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = poppinsFontFamily,
                        fontSize = 30.sp,
                        modifier = Modifier.weight(1f),
                        text = "$138.99"
                    )
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color("#29bf12".toColorInt())
                        ),
                        modifier = Modifier
                            .height(55.dp)
                            .width(170.dp),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            fontFamily = poppinsFontFamily,
                            text = "Add to Cart",
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun DetailScreenPreview() {
    VibeStoreTheme {
        DetailScreen()
    }
}
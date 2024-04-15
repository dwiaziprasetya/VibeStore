package com.example.vibestore.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.vibestore.R
import com.example.vibestore.helper.ViewModelFactory
import com.example.vibestore.ui.component.ExpandingText
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    productId: Int,
    onBackClick: () -> Unit,
    navigateToCart: () -> Unit
) {
    val viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(id = productId)
    )

    val product by viewModel.product.observeAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(horizontal = 16.dp),
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
                        imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                        tint = Color.Black,
                        contentDescription = "back",
                        modifier = Modifier.clickable {
                            onBackClick()
                        }
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
            if (product == null) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(64.dp)
                        .align(Alignment.Center),
                    color = Color("#29bf12".toColorInt()),
                )
            } else {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    AsyncImage(
                        model = product?.image,
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 16.dp,
                            )
                            .height(300.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentDescription = "Product Image"
                    )
                    Text(
                        modifier = Modifier.padding(
                            top = 16.dp,
                        ),
                        text = product?.title.toString(),
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
                            text = product?.rating?.rate.toString(),
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = "(${product?.rating?.count.toString()})",
                            color = MaterialTheme.colorScheme.outline,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Light
                        )
                    }
                    Text(
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 32.dp),
                        text = "Description Product",
                        fontFamily = poppinsFontFamily
                    )
                    product?.description?.let {
                        ExpandingText(
                            modifier = Modifier.padding(top = 8.dp),
                            text = it,
                            fontSize = (13.5).sp
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .height(85.dp)
                        .background(Color.White)
                ){
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
                                .weight(
                                    1f
                                ),
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 40.sp,
                            text = "$${product?.price?.toString()}"
                        )
                        Button(
                            modifier = Modifier
                                .height(55.dp)
                                .width(170.dp),
                            shape = RoundedCornerShape(10.dp),
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color("#29bf12".toColorInt())
                            )
                        ) {
                            Text(
                                fontFamily = poppinsFontFamily,
                                text = "Checkout",
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .clickable {
                                        navigateToCart()
                                    }
                            )
                        }
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
        DetailScreen(2, onBackClick = {}, navigateToCart = {})
    }
}
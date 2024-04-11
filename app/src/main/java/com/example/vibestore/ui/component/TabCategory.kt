package com.example.vibestore.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vibestore.model.nameCategory
import com.example.vibestore.ui.screen.product.ProductScreen
import com.example.vibestore.ui.screen.product.ProductViewModel
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabCategory(
    viewModel: ProductViewModel = viewModel()
) {

    val pagerState = rememberPagerState(pageCount = {
        nameCategory.size
    })

    val coroutineScope = rememberCoroutineScope()

    Column {
        ScrollableTabRow(
            edgePadding = 0.dp,
            backgroundColor = Color.Transparent,
            selectedTabIndex = pagerState.currentPage,
            contentColor = MaterialTheme.colorScheme.outline,
            divider = {},
            modifier = Modifier
                .fillMaxWidth(),
            indicator = {
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(it[pagerState.currentPage])
                        .height(TabRowDefaults.IndicatorHeight * 2)
                        .padding(start = 20.dp, end = 20.dp)
                        .background(
                            color = Color("#29bf12".toColorInt()),
                            shape = RoundedCornerShape(50.dp)
                        )
                )
            }
        ) {
            val tabItems = nameCategory
            tabItems.forEachIndexed { index, productCategory ->
                Tab(
                    text = {
                        if (index == pagerState.currentPage) {
                            Text(
                                text = productCategory.nameCategory,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.Medium,
                                color = Color("#29bf12".toColorInt())
                            )
                        } else {
                            Text(
                                text = productCategory.nameCategory,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.outline
                            )
                        }
                    },
                    selected = index == pagerState.currentPage,
                    modifier = Modifier
                        .wrapContentWidth(),
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false
        ) { page ->
            Column(
                modifier = Modifier.wrapContentHeight(),
            ) {
                when (page) {
                    0 -> ProductScreen(viewModel)
                    1 -> Text("Page men")
                    2 -> Text("Page women")
                    3 -> Text("Page electronics")
                    4 -> Text("Page jewelery")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TabCategoryPreview() {
    VibeStoreTheme {
        TabCategory()
    }
}
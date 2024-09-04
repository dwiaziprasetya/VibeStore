package com.example.vibestore.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.vibestore.ui.screen.product.all.AllProductScreen
import com.example.vibestore.ui.screen.product.electronic.ElectronicProductScreen
import com.example.vibestore.ui.screen.product.jewelery.JeweleryProductScreen
import com.example.vibestore.ui.screen.product.men.MenProductScreen
import com.example.vibestore.ui.screen.product.women.WomenProductScreen
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabCategory(
    modifier: Modifier = Modifier,
    count: Int = 4,
    heightContent: Dp = Dp.Unspecified,
    limit: Int = 20,
    gridHeight: Dp = Dp.Unspecified,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
    navigateToDetail: (Int) -> Unit
) {

    val category by remember {
        mutableStateOf(
            listOf(
                "All",
                "Men",
                "Women",
                "Electronics",
                "Jewelery",
            )
        )
    }

    val pagerState = rememberPagerState(pageCount = {
        category.size
    })

    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier) {
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
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(50.dp)
                        )
                )
            }
        ) {
            val tabItems = category
            tabItems.forEachIndexed { index, _ ->
                Tab(
                    text = {
                        if (index == pagerState.currentPage) {
                            Text(
                                text = category[index],
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        } else {
                            Text(
                                text = category[index],
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
            userScrollEnabled = true
        ) { page ->
            Box(modifier = Modifier.fillMaxHeight()){
                when (page) {
                    0 -> {
                        AllProductScreen(
                            gridHeight, limit,
                            navigateToDetail = navigateToDetail,
                            count = count,
                            height = heightContent,
                            snackbarHostState = snackbarHostState,
                            scope = scope
                        )
                    } 1 -> {
                        MenProductScreen(
                            gridHeight, limit,
                            navigateToDetail = navigateToDetail,
                            height = heightContent,
                            scope = scope,
                            snackbarHostState = snackbarHostState
                        )
                    } 2 -> {
                        WomenProductScreen(
                            gridHeight, limit,
                            navigateToDetail = navigateToDetail,
                            count = count,
                            height = heightContent,
                            scope = scope,
                            snackbarHostState = snackbarHostState
                        )
                    } 3 -> {
                        ElectronicProductScreen(
                            gridHeight, limit,
                            navigateToDetail = navigateToDetail,
                            count = count,
                            height = heightContent,
                            scope = scope,
                            snackbarHostState = snackbarHostState
                        )
                    } 4 -> {
                        JeweleryProductScreen(
                            gridHeight, limit,
                            navigateToDetail = navigateToDetail,
                            count = count,
                            height = heightContent,
                            scope = scope,
                            snackbarHostState = snackbarHostState
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TabCategoryPreview() {
    VibeStoreTheme {
        TabCategory(
            navigateToDetail = {},
            snackbarHostState = SnackbarHostState(),
            scope = rememberCoroutineScope()
        )
    }
}
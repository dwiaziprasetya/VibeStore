package com.example.vibestore.ui.screen.categories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vibestore.R
import com.example.vibestore.ui.component.TabCategory
import com.example.vibestore.ui.navigation.model.Screen
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    navcontroller: NavHostController
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = {
                    Text(
                        text = stringResource(R.string.categories),
                        fontFamily = poppinsFontFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back",
                        modifier = Modifier.clickable { navcontroller.navigateUp() }
                    )
                },
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
        ) {
            TabCategory(
                navigateToDetail = {
                    navcontroller.navigate(Screen.DetailProduct.createRoute(it))
                },
                count = 10,
                gridHeight = 1000.dp
            )
        }
    }
}

@Preview
@Composable
private fun CategoriesPreview() {
    VibeStoreTheme {
        CategoriesScreen(rememberNavController())
    }
}
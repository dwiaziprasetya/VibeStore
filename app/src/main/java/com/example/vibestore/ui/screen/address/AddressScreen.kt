package com.example.vibestore.ui.screen.address

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.vibestore.R
import com.example.vibestore.ui.component.AddressItemScreen
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(
    navHostController: NavHostController
) {
    Scaffold(
        topBar = {
           CenterAlignedTopAppBar(
               modifier = Modifier
                   .padding(horizontal = 16.dp)
                   .statusBarsPadding(),
               title = {
                   Text(
                       text = stringResource(R.string.address),
                       fontFamily = poppinsFontFamily,
                       fontSize = 20.sp,
                       fontWeight = FontWeight.SemiBold
                   )
               },
               navigationIcon = {
                   Icon(
                       imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                       contentDescription = "back",
                       modifier = Modifier
                           .clickable {
                               navHostController.navigateUp()
                           }
                   )
               }
           )
        }
    ) { innerPadding ->
        AddressContent(
            modifier = Modifier.padding(innerPadding),
            name = "Dwi Azi Prasetya",
            address = "Jl. Durian No. 123, Banyubiru " +
                    "Kab. Semarang, Jawa Tengah " +
                    "Indonesia, 50123"
        )
    }
}

@Composable
fun AddressContent(
    modifier: Modifier = Modifier,
    name: String,
    address: String
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        AddressItemScreen(name = name, address = address)
        AddressItemScreen(name = name, address = address)
        Card(
            border = BorderStroke(
                width = 1.dp,
                color = Color.LightGray
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            modifier = Modifier
                .padding(top = 64.dp)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Add New Address",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = poppinsFontFamily,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Preview
@Composable
private fun AddressScreenPreview() {
    VibeStoreTheme {
        AddressScreen(
            rememberNavController()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddressContentPreview() {
    VibeStoreTheme {
        AddressContent(
            name = "Dwi Azi Prasetya",
            address = "Jl. Durian No. 123, Banyubiru " +
                    "Kab. Semarang, Jawa Tengah " +
                    "Indonesia, 50123"
        )
    }
}
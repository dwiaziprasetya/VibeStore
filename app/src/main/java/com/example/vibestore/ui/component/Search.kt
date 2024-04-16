package com.example.vibestore.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search() {
    SearchBar(
        query = "",
        onQueryChange = {},
        onSearch = {},
        colors = SearchBarDefaults.colors(
            containerColor = Color("#EEEEEE".toColorInt())
        ),
        active = false,
        onActiveChange = {},
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.outline
            )
        },
        placeholder = {
            Text(
                text = "Search product...",
                color = MaterialTheme.colorScheme.outline,
                fontSize = 16.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal,
            )
        }
    ) {
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchPreview() {
    VibeStoreTheme {
        Search()
    }
}
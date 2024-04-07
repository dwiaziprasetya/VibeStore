package com.example.vibestore.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.vibestore.ui.theme.VibeStoreTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search() {
    Row(
        verticalAlignment = Alignment.Bottom
    ) {
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
                .weight(2f)
                .fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            placeholder = {
                Text(
                    text = "Search product...",
                    color = MaterialTheme.colorScheme.outline,
                    fontSize = 17.sp
                )
            }
        ) {
        }
        Box(
            modifier = Modifier
                .padding(
                    start = 0.dp,
                    end = 16.dp,
                    top = 16.dp,
                    bottom = 16.dp
                )
                .size(56.dp)
                .background(
                    color = Color("#EEEEEE".toColorInt()),
                    shape = RoundedCornerShape(50.dp)
                )
                .padding(14.dp),
        ){
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .size(24.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchPreview() {
    VibeStoreTheme {
        Search()
    }
}
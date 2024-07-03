package com.example.vibestore.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.vibestore.R
import com.example.vibestore.ui.theme.VibeStoreTheme

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {},
    onSortChange: (String) -> Unit = {}
) {
    var isAscending by remember { mutableStateOf(true) }
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Search(
            modifier = Modifier.weight(1f),
            onSearch = onSearch
        )
        Surface(
            modifier = Modifier
                .padding(start = 8.dp)
                .size(40.dp),
            shape = CircleShape,
            color = Color("#29bf12".toColorInt())
        ) {
            Icon(
                painter = painterResource(
                    if (isAscending) R.drawable.icon_sort_asc
                    else R.drawable.icon_desc_sort
                ),
                contentDescription = if (isAscending) "Sort Ascending" else "Sort Descending",
                tint = MaterialTheme.colorScheme.surface,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable {
                        isAscending = !isAscending
                        onSortChange(
                            if (isAscending) "asc" else "desc"
                        )
                    }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    VibeStoreTheme {
        SearchBar()
    }
}
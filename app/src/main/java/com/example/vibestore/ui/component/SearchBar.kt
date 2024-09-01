package com.example.vibestore.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vibestore.ui.theme.VibeStoreTheme
import com.example.vibestore.ui.theme.poppinsFontFamily

// Sorting Feature Disable

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {},
//    onSortChange: (String) -> Unit = {}
) {

//    var isAscending by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier.padding(
            start = 16.dp,
            end = 16.dp,
            top = 8.dp,
            bottom = 8.dp
        )
    ) {
        Text(
            text = "Search",
            modifier = Modifier.padding(
                start = 16.dp,
                top = 8.dp
            ),
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp,
            fontFamily = poppinsFontFamily
        )

        Row(
            modifier = modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Search(
                modifier = Modifier.weight(1f),
                onSearch = onSearch
            )
//            Surface(
//                modifier = Modifier
//                    .padding(start = 8.dp)
//                    .size(40.dp),
//                shape = CircleShape,
//                color = MaterialTheme.colorScheme.primary,
//            ) {
//                Icon(
//                    painter = painterResource(
//                        if (isAscending) R.drawable.icon_sort_asc
//                        else R.drawable.icon_desc_sort
//                    ),
//                    contentDescription = if (isAscending) "Sort Ascending" else "Sort Descending",
//                    tint = MaterialTheme.colorScheme.surface,
//                    modifier = Modifier
//                        .padding(8.dp)
//                        .clickable {
//                            isAscending = !isAscending
//                            onSortChange(
//                                if (isAscending) "asc" else "desc"
//                            )
//                        }
//                )
//            }
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
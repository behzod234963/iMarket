package com.mr.anonym.imarket.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreenTopAppBar(
    category: String,
    onNavigationClick:()->Unit,
    onSearchClick:()->Unit
) {

    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = category,
                    color = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
            }
        },
        navigationIcon = {
            IconButton(
                onClick = { onNavigationClick() }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "icon back",
                    tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                )
            }
        },
        actions = {
            IconButton(
                onClick = { onSearchClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "icon button search",
                    tint = if (isSystemInDarkTheme())Color.White else Color.Black
                )
            }
        },
    )
}

@Preview
@Composable
private fun PreviewCategoryScreenTopAppBar() {
    CategoryScreenTopAppBar(
        category = "",
        onNavigationClick = {},
        onSearchClick = {}
    )
}
package com.mr.anonym.imarket.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultMediumTopAppBar(
    modifier: Modifier = Modifier,
    colors: TopAppBarColors,
    title: @Composable ()->Unit,
    navigationIcon: @Composable ()->Unit,
    actions: @Composable ()->Unit,
) {
    MediumTopAppBar(
        modifier = modifier,
        colors = colors,
        title = { title() },
        navigationIcon = { navigationIcon() },
        actions = { actions() }
    )
}
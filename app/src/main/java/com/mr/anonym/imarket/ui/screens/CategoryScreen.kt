package com.mr.anonym.imarket.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mr.anonym.imarket.R
import com.mr.anonym.imarket.presentation.navigation.NavigationArguments
import com.mr.anonym.imarket.presentation.navigation.Screens
import com.mr.anonym.imarket.presentation.viewModel.CategoryViewModel
import com.mr.anonym.imarket.ui.components.CategoryScreenTopAppBar
import com.mr.anonym.imarket.ui.components.FilterView
import com.mr.anonym.imarket.ui.components.SortView
import com.mr.anonym.imarket.ui.theme.darkerGray

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoryScreen(
    viewModel:CategoryViewModel = hiltViewModel(),
    navController:NavHostController,
    arguments:NavigationArguments,
) {

    val category = viewModel.category

    val scrollState = rememberScrollState()

    val isExpanded = remember { mutableStateOf( false ) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) darkerGray else Color.White)
    ) {
        CategoryScreenTopAppBar(
            category = category.value,
            onNavigationClick = {
                navController.popBackStack()
            },
            onSearchClick = {
                navController.navigate(Screens.SearchFieldScreen.route)
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize(0.9f)
                .scrollable(state = scrollState, orientation = Orientation.Vertical)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
//                Sort products content
                SortView(
                    value = "Sort view text",
                    onValueChange = { TODO() },
                    isExpanded = isExpanded.value,
                    onExpandedChange = {
                        isExpanded.value = !isExpanded.value
                    },
                    onDismissRequest = { TODO() },
                    content = {
                        TODO()
                    }
                )
//                Filter products content
                Row(
                    modifier = Modifier
                        .height(50.dp)
                        .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
                        .clickable { navController.navigate(Screens.FilterView.route) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        painter = painterResource(R.drawable.ic_settings),
                        contentDescription = "icon filter",
                        tint = if(isSystemInDarkTheme())Color.White else Color.Black
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 5.dp),
                        text = stringResource(R.string.filter),
                        color = if(isSystemInDarkTheme())Color.White else Color.Black,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
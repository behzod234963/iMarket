package com.mr.anonym.imarket.ui.screens

import android.annotation.SuppressLint
import android.app.Activity
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mr.anonym.domain.components.SortType
import com.mr.anonym.imarket.R
import com.mr.anonym.imarket.presentation.navigation.NavigationArguments
import com.mr.anonym.imarket.presentation.navigation.Screens
import com.mr.anonym.imarket.presentation.viewModel.CategoryViewModel
import com.mr.anonym.imarket.ui.components.CategoryScreenTopAppBar
import com.mr.anonym.imarket.ui.components.SortView
import com.mr.anonym.imarket.ui.theme.darkerGray

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel = hiltViewModel(),
    navController: NavHostController,
    arguments: NavigationArguments,
) {

    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val activityContext = LocalContext.current as Activity
    val coroutineScope = rememberCoroutineScope()

    val valueTextIndex = remember { mutableIntStateOf(0) }

    val category = viewModel.category

    val isExpanded = remember { mutableStateOf(false) }
    val isFilterApplied = viewModel.isFilterApplied

    val products = viewModel.products
    val filteredProducts = viewModel.filteredProducts

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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
//                Sort products content
                SortView(
                    value = when (valueTextIndex.intValue) {
                        0 -> {
                            stringResource(R.string.before_inexpensive)
                        }

                        1 -> {
                            stringResource(R.string.before_expensive)
                        }

                        2 -> {
                            stringResource(R.string.best_rating)
                        }

                        3 -> {
                            stringResource(R.string.by_discount)
                        }

                        else -> stringResource(R.string.by_reviews)
                    },
                    onValueChange = { },
                    isExpanded = isExpanded.value,
                    onExpandedChange = {
                        isExpanded.value = !isExpanded.value
                    },
                    onDismissRequest = {
                        isExpanded.value = false
                    },
                    content = {
                        Column {
                            MenuItemContent(
                                onInexpensiveClick = {
                                    viewModel.onSortEvent(SortType.Inexpensive)
                                    valueTextIndex.intValue = 0
                                    isExpanded.value = false
                                },
                                onExpensiveClick = {
                                    viewModel.onSortEvent(SortType.Inexpensive)
                                    valueTextIndex.intValue = 1
                                    isExpanded.value = false
                                },
                                onBestRatingClick = {
                                    viewModel.onSortEvent(SortType.Inexpensive)
                                    valueTextIndex.intValue = 2
                                    isExpanded.value = false
                                },
                                onDiscountClick = {
                                    viewModel.onSortEvent(SortType.Inexpensive)
                                    valueTextIndex.intValue = 3
                                    isExpanded.value = false
                                },
                                onReviewsClick = {
                                    viewModel.onSortEvent(SortType.Inexpensive)
                                    valueTextIndex.intValue = 4
                                    isExpanded.value = false
                                }
                            )
                        }
                    }
                )
//                Filter products content
                Row(
                    modifier = Modifier
                        .height(50.dp)
                        .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
                        .clickable { navController.navigate(Screens.FilterView.route + "/${category.value}") },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        painter = painterResource(R.drawable.ic_settings),
                        contentDescription = "icon filter",
                        tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 5.dp),
                        text = stringResource(R.string.filter),
                        color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                        fontSize = 16.sp
                    )
                }
            }
        }
        LazyColumn{
            if (isFilterApplied.value){
                items(filteredProducts.value){
                    TODO()
                }
            }
        }
    }
}

@Composable
fun MenuItemContent(
    onInexpensiveClick: () -> Unit,
    onExpensiveClick: () -> Unit,
    onBestRatingClick: () -> Unit,
    onDiscountClick: () -> Unit,
    onReviewsClick: () -> Unit,
) {

    DropdownMenuItem(
        text = {
            Text(
                text = stringResource(R.string.before_inexpensive),
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontSize = 18.sp
            )
        },
        onClick = { onInexpensiveClick() },
    )
    DropdownMenuItem(
        text = {
            Text(
                text = stringResource(R.string.before_expensive),
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontSize = 18.sp
            )
        },
        onClick = { onExpensiveClick() }
    )
    DropdownMenuItem(
        text = {
            Text(
                text = stringResource(R.string.best_rating),
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontSize = 18.sp
            )
        },
        onClick = { onBestRatingClick() }
    )
    DropdownMenuItem(
        text = {
            Text(
                text = stringResource(R.string.by_discount),
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontSize = 18.sp
            )
        },
        onClick = { onDiscountClick() }
    )
    DropdownMenuItem(
        text = {
            Text(
                text = stringResource(R.string.by_reviews),
                color = if (isSystemInDarkTheme()) Color.White else Color.Black,
                fontSize = 18.sp
            )
        },
        onClick = { onReviewsClick() }
    )
}
package com.mr.anonym.imarket.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.mr.anonym.domain.model.SearchHistoryModel
import com.mr.anonym.imarket.R
import com.mr.anonym.imarket.presentation.navigation.Screens
import com.mr.anonym.imarket.presentation.utils.LocalDataEvent
import com.mr.anonym.imarket.presentation.viewModel.SearchFieldViewModel
import com.mr.anonym.imarket.ui.components.SearchFieldTopAppBar
import com.mr.anonym.imarket.ui.items.SearchFieldItem
import com.mr.anonym.imarket.ui.items.SearchHistoryItem
import com.mr.anonym.imarket.ui.theme.darkerGray

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchFieldScreen(
    navController: NavHostController,
    viewModel: SearchFieldViewModel = hiltViewModel()
) {

    val searchText = remember { mutableStateOf("") }

    val searchResults = viewModel.searchingResource
    val categories = viewModel.categories
    val searchHistory = viewModel.searchHistory

    val isSearching = viewModel.isSearching
    val isSearchFieldEmpty = remember { mutableStateOf(false) }

    if (searchText.value.isBlank() || searchText.value.isEmpty()) {
        isSearchFieldEmpty.value = true
    } else {
        isSearchFieldEmpty.value = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) darkerGray else Color.White)
    ) {
        SearchFieldTopAppBar(
            text = searchText.value,
            navController = navController,
            onValueChange = {
                searchText.value = it
            },
            onClear = {
                searchText.value = ""
                isSearchFieldEmpty.value = true
            },
            onSend = {
                viewModel.getSearchedProducts(searchText.value)
                viewModel.getAllCategory()
                if (searchText.value != "") {
                    viewModel.onLocalDataEvent(
                        LocalDataEvent.InsertSearchHistory(
                            SearchHistoryModel(title = searchText.value)
                        )
                    )
                }
            },
        )
        Column(
            modifier = Modifier
                .background(if (isSystemInDarkTheme()) darkerGray else Color.White)
                .height(70.dp)
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.history),
                    fontSize = 16.sp,
                    color = Color.LightGray
                )
//                Text button clear search field
                TextButton(
                    onClick = {
                        viewModel.onLocalDataEvent(LocalDataEvent.ClearHistory(searchHistory.value))
                        viewModel.onLocalDataEvent(LocalDataEvent.GetAllSearchedHistory)
                    }
                ) {
                    Text(
                        text = stringResource(R.string.clear),
                        fontSize = 16.sp,
                        color = Color.Blue
                    )
                }
            }
        }
        if (isSearching.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(100.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                CircularProgressIndicator()
            }
        } else {
            when (isSearchFieldEmpty.value) {
                true -> {
                    viewModel.onLocalDataEvent(LocalDataEvent.GetAllSearchedHistory)
                    LazyColumn {
                        items(
                            items = searchHistory.value,
                            key = { it.toString() }) { history ->
                            SearchHistoryItem(
                                historyModel = history
                            ) {
                                searchText.value = history.title
                                isSearchFieldEmpty.value = false
                            }
                        }
                    }
                }

                false -> {
                    LazyColumn {
                        items(
                            items = searchResults.value
                        ) { results ->
                            SearchFieldItem(
                                productsItem = results,
                                onIconClick = {
                                    searchText.value = results.title.toString()
                                },
                                onCardClick = {
                                    navController.navigate(Screens.ProductScreen.route + "/${results.id}")
                                },
                                categoryText = {
                                    for (i in categories.value) {
                                        val category = i.name?.lowercase()
                                        if (category?.contains(searchText.value) == true) {
                                            TextButton(
                                                onClick = {
                                                    navController.navigate(Screens.CategoryScreen.route + "/${i.name}")
                                                }
                                            ) {
                                                Text(
                                                    text = i.name!!,
                                                    color = Color.Blue,
                                                    fontSize = 15.sp,
                                                )
                                            }
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
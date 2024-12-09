package com.mr.anonym.imarket.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mr.anonym.imarket.presentation.navigation.NavigationArguments
import com.mr.anonym.imarket.presentation.viewModel.CategoryViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoryScreen(
    arguments:NavigationArguments,
    viewModel: CategoryViewModel = hiltViewModel()
) {

    val category = viewModel.category

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = category.value
                    )
                }
            )
        }
    ){

    }
}

@Preview
@Composable
private fun PreviewCategoryScreen() {
    CategoryScreen(
        arguments = NavigationArguments("Laptops",)
    )
}
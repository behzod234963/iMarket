package com.mr.anonym.imarket.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mr.anonym.imarket.ui.components.FilterView
import com.mr.anonym.imarket.ui.screens.CategoryScreen
import com.mr.anonym.imarket.ui.screens.ChangeCityScreen
import com.mr.anonym.imarket.ui.screens.MainScreen
import com.mr.anonym.imarket.ui.screens.OnBoardingScreen
import com.mr.anonym.imarket.ui.screens.ProductScreen
import com.mr.anonym.imarket.ui.screens.SearchFieldScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Screens.SearchFieldScreen.route
    ) {
        composable(Screens.OnBoardingScreen.route) {
            OnBoardingScreen(navController)
        }
        composable(Screens.ChangeCityScreen.route) {
            ChangeCityScreen(navController)
        }
        composable(Screens.MainScreen.route) {
            MainScreen(navController)
        }
        composable(Screens.SearchFieldScreen.route) {
            SearchFieldScreen(navController)
        }
        composable(
            Screens.CategoryScreen.route + "/{category}",
            arguments = listOf(
                navArgument(name = "category") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {entry->
            val category = entry.arguments?.getString("category")?:""
            CategoryScreen(
                navController = navController,
                arguments = NavigationArguments(category)
            )
        }
        composable(
            Screens.ProductScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {entry->
            val id = entry.arguments?.getInt("id")?:-1
            ProductScreen(arguments = NavigationArguments(id = id))
        }
        composable(Screens.FilterView.route) {
            FilterView (
                ,
                onBackClick = {}
            )
        }
    }
}
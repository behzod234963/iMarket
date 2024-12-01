package com.mr.anonym.imarket.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mr.anonym.imarket.ui.screens.OnBoardingScreen
import com.mr.anonym.imarket.ui.screens.ChangeCityScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Screens.OnBoardingScreen.route
    ){
        composable(Screens.OnBoardingScreen.route){
            OnBoardingScreen(navController)
        }
        composable(Screens.ChangeCityScreen.route) {
            ChangeCityScreen(navController)
        }
    }
}
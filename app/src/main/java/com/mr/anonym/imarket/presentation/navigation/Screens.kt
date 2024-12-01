package com.mr.anonym.imarket.presentation.navigation

sealed class Screens(val route:String){
    data object OnBoardingScreen:Screens("OnBoardingScreen")
    data object ChangeCityScreen:Screens("ChangeCityScreen")
}

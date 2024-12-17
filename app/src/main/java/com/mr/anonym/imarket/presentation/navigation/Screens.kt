package com.mr.anonym.imarket.presentation.navigation

sealed class Screens(val route:String){
    data object OnBoardingScreen:Screens("OnBoardingScreen")
    data object ChangeCityScreen:Screens("ChangeCityScreen")
    data object MainScreen:Screens("MainScreen")
    data object SearchFieldScreen:Screens("SearchFieldScreen")
    data object CategoryScreen:Screens("CategoryScreen")
    data object ProductScreen:Screens("ProductScreen")
    data object FilterView:Screens("FilterView")
}

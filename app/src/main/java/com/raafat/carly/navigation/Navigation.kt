package com.raafat.carly.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


sealed class Screen(val route: String){
    data object Dashboard: Screen("dashboard")
    data object CarCreation: Screen("cars_creation")
    data object CarSelection: Screen("car_selection")
}


class NavActions(private val navigator: ScreenNavigator) {
    fun goToUserCars() = navigator.navigate(Screen.CarSelection)
    fun goToCarCreation() = navigator.navigate(Screen.CarCreation)
    fun pop() = navigator.pop()
}

class ScreenNavigator(private val navController: NavController) {
    fun navigate(screen: Screen) = navController.navigate(screen.route)
    fun pop() = navController.popBackStack()
}

fun NavGraphBuilder.addScreen(screen: Screen, content: @Composable (NavBackStackEntry) -> Unit) = composable(screen.route) { content(it) }
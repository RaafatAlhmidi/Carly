package com.raafat.carly.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.raafat.carly.navigation.Screen.Dashboard


sealed class Screen(val route: String) {
    data object Dashboard : Screen("dashboard")
    data object CarCreation : Screen("cars_creation")
    data object CarSelection : Screen("car_selection")
}


class NavActions(private val navigator: ScreenNavigator) {
    fun goToUserCars() = navigator.navigate(Screen.CarSelection)
    fun goToCarCreation() = navigator.navigate(Screen.CarCreation, navOptions = navOptions {
        popUpTo(Dashboard.route) { inclusive = false }
    })

    fun goToDashboard() = navigator.navigate(Dashboard, navOptions = navOptions {
        popUpTo(Dashboard.route) { inclusive = true }
    })

    fun pop() = navigator.pop()
}

class ScreenNavigator(private val navController: NavController) {
    fun navigate(
        screen: Screen,
        navOptions: NavOptions? = null,
    ) = navController.navigate(screen.route, navOptions)

    fun pop() = navController.popBackStack()
}

fun NavGraphBuilder.addScreen(screen: Screen, content: @Composable (NavBackStackEntry) -> Unit) = composable(screen.route) { content(it) }
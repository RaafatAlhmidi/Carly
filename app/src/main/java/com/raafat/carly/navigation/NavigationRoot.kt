package com.raafat.carly.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.raafat.carly.screens.dashboard.DashboardScreen

@Composable
fun NavigationRoot() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Dashboard.route) {
        val navActions = NavActions(ScreenNavigator(navController))
        addScreen(Screen.Dashboard) { DashboardScreen() }
    }
}

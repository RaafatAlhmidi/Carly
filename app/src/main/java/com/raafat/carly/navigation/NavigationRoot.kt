package com.raafat.carly.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.raafat.carly.navigation.Screen.CarCreation
import com.raafat.carly.navigation.Screen.Dashboard
import com.raafat.carly.screens.carCreation.CarCreationScreen
import com.raafat.carly.screens.dashboard.DashboardScreen
import com.raafat.carly.ui.theme.CarlyTheme

@Composable
fun NavigationRoot() {
    val navController = rememberNavController()
    CarlyTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) { innerPadding ->
            NavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController, startDestination = Dashboard.route) {
                val navActions = NavActions(ScreenNavigator(navController))
                addScreen(Dashboard) { DashboardScreen(
                    goToCarSelection = navActions::goToCarCreation,
                    goToCarCreation = navActions::goToCarCreation
                ) }
                addScreen(CarCreation){ CarCreationScreen(goBack = navActions::pop) }
            }
        }
    }
}

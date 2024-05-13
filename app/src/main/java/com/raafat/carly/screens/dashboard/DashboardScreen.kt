package com.raafat.carly.screens.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.raafat.carly.R


@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    goToCarCreation: () -> Unit,
    goToCarSelection: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.dashboard_background),
                contentScale = ContentScale.FillBounds
            )
    ) {

        Icon(
            modifier = Modifier
                .size(130.dp)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.carly_logo),
            contentDescription = null
        )
        DashboardUi(
            modifier = Modifier.fillMaxSize(),
            uiState = uiState,
            goToCarCreation = goToCarCreation,
            goToCarSelection = goToCarSelection
        )


    }
}


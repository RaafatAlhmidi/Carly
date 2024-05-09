package com.raafat.carly.screens.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.raafat.carly.R
import com.raafat.carly.ui.theme.CarlyTheme


@Composable
fun DashboardScreen() {
    CarlyTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
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

                Box(modifier = Modifier.fillMaxSize()) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(20.dp)
                            .size(120.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}
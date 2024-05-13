package com.raafat.carly.screens.dashboard

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raafat.carly.R
import com.raafat.carly.screens.dashboard.DashboardUiState.Success
import com.raafat.carly.ui.theme.backgroundDark
import com.raafat.carly.ui.theme.backgroundLight
import com.raafat.carly.uitils.CarlyDarkText
import com.raafat.carly.uitils.CarlyLightText
import com.raafat.data.model.Car
import com.raafat.data.model.Feature

@Composable
fun DashboardUi(
    modifier: Modifier,
    uiState: DashboardUiState,
    goToCarCreation: () -> Unit,
    goToCarSelection: () -> Unit
) {

    AnimatedContent(targetState = uiState is DashboardUiState.Empty, label = "") { isEmpty ->

        if (isEmpty) {
            AddButton(goToCarCreation)
        } else {
            (uiState as? Success)?.let {
                Content(
                    modifier = modifier,
                    car = it.selectedCar,
                    features = it.features,
                    goToCarSelection = goToCarSelection
                )
            }
        }
    }
}

@Composable
private fun Content(
    modifier: Modifier,
    car: Car?,
    features: List<Feature>,
    goToCarSelection: () -> Unit

) {
    Column(modifier = modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .weight(2f)
        ) {
            Row(modifier = Modifier.weight(1f)) {
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    CarlyLightText(text = car?.title ?: "N/A", textSize = 20.sp)
                    CarlyDarkText(text = car?.subTitle ?: "N/A")
                }

                Image(
                    modifier = Modifier
                        .size(50.dp)
                        .clickable {
                            goToCarSelection()
                        },
                    painter = painterResource(id = R.drawable.list_icon),
                    contentDescription = null
                )
            }
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                painter = painterResource(id = R.drawable.car_image), contentDescription = null
            )
        }


        Column(modifier = Modifier.weight(1f)) {

            CarlyLightText(text = "Discover your car", textSize = 20.sp)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(6.dp))
            ) {

                features.onEachIndexed { index, feature ->
                    FeatureRow(modifier = Modifier, feature = feature)
                    if (index != features.lastIndex) {
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
private fun FeatureRow(
    modifier: Modifier,
    feature: Feature
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundLight)
            .padding(horizontal = 10.dp, vertical = 25.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CarlyDarkText(text = feature.name, modifier = Modifier.weight(1f))

        Icon(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape)
                .background(backgroundDark)
                .padding(5.dp),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null
        )
    }
}

@Composable
private fun AddButton(
    goToCarCreation: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = null,
            modifier = Modifier
                .padding(20.dp)
                .size(120.dp)
                .align(Alignment.Center)
                .clickable {
                    goToCarCreation()
                }
        )
    }
}
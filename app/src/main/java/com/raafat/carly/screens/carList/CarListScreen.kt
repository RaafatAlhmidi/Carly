package com.raafat.carly.screens.carList

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.AutoMirrored.Filled
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.raafat.carly.screens.carList.CarListUiState.Success
import com.raafat.carly.ui.theme.backgroundDark
import com.raafat.carly.ui.theme.backgroundLight
import com.raafat.carly.ui.theme.primaryColor
import com.raafat.carly.uitils.CarlyDarkText
import com.raafat.carly.uitils.CarlyLightText
import com.raafat.data.model.Car


@Composable
fun CarListScreen(
    viewModel: CarListViewModel = hiltViewModel(),
    goBack: () -> Unit
) {
    BackHandler {
        goBack()
    }

    val uiState by viewModel.uiState.collectAsState()
    val isLoadingTransition = updateTransition(targetState = uiState is Success, label = "")

    Column(
        modifier = Modifier
            .background(backgroundLight)
            .fillMaxSize()
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 7.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        goBack()

                    },
                imageVector = Filled.KeyboardArrowLeft,
                contentDescription = null,
                tint = Color.White
            )

            CarlyLightText(text = "Your Cars", textSize = 18.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        }

        if (uiState is Success) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                val cars = (uiState as Success).cars
                items(cars.size) { index: Int ->
                    val car = cars[index]
                    CarItem(
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                            .clickable {
                                viewModel.selectCar(car)
                            }, car = car
                    ) {
                        viewModel.deleteCar(it)
                    }
                }
            }
        }

        OutlinedButton(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(50.dp))
                .background(primaryColor),
            onClick = { }) {
            CarlyLightText(text = "Add new car")
        }
    }
}

@Composable
fun CarItem(
    modifier: Modifier,
    car: Car,
    deleteCar: (Car) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundDark)
            .clip(RoundedCornerShape(5.dp))
            .border(1.dp, if (car.isSelected) primaryColor else Color.Unspecified, RoundedCornerShape(5.dp))
            .padding(horizontal = 10.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            CarlyLightText(text = "${car.brand.name} - ${car.series.name}")
            CarlyDarkText(text = "${car.year} - ${car.fuelType.name}", textSize = 14.sp)
        }

        if (!car.isSelected)
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        deleteCar(car)
                    },
                imageVector = Icons.Default.Delete,
                contentDescription = null, tint = Color.White
            )
    }
}
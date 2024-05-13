package com.raafat.carly.screens.carCreation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import com.raafat.carly.ui.theme.backgroundDark
import com.raafat.carly.ui.theme.backgroundLight
import com.raafat.carly.uitils.CarlyDarkText
import com.raafat.carly.uitils.CarlyLightText
import com.raafat.carly.uitils.CarlyPrimaryText
import com.raafat.data.model.Brand
import com.raafat.data.model.FuelType
import com.raafat.data.model.Series

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarCreationScreen(
    viewmodel: CarCreationViewModel = hiltViewModel(),
    goBack: () -> Unit,
    goToCarsList: () -> Unit
) {
    BackHandler {
        if (viewmodel.prevState())
            goBack()
    }

    val uiState by viewmodel.uiState.collectAsState()

    if (uiState is Finish)
        goToCarsList()

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
                        if (viewmodel.prevState())
                            goBack()

                    },
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = null,
                tint = Color.White
            )

            CarlyLightText(text = "Car Selection", textSize = 18.sp, modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        }

        AnimatedVisibility(visible = uiState.onGoingSelectionText != null) {
            uiState.onGoingSelectionText?.let {
                CarlyPrimaryText(
                    text = it,
                    modifier = Modifier
                        .animateContentSize()
                        .padding(vertical = 5.dp, horizontal = 10.dp),
                    textSize = 14.sp
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            userScrollEnabled = true,
            state = rememberLazyListState()
        ) {
            val items = when (uiState) {
                is SelectBrands -> (uiState as SelectBrands).items
                is SelectSeries -> (uiState as SelectSeries).items
                is SelectYear -> (uiState as SelectYear).items
                is SelectFuelType -> (uiState as SelectFuelType).items
                else -> emptyList()
            }

            items(items.size, key = { items[it].hashCode() }) { index ->
                val item = items[index]

                when (uiState) {
                    is SelectBrands -> {
                        BrandSelectionRow(
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .clickable {
                                    viewmodel.selectBrand(item as Brand)
                                },
                            text = (item as Brand).name
                        )
                    }

                    is SelectSeries -> {
                        BrandSelectionRow(
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .animateItemPlacement()
                                .clickable {
                                    viewmodel.selectSeries(item as Series)
                                },
                            text = (item as Series).name
                        )
                    }

                    is SelectYear -> {
                        BrandSelectionRow(
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .animateItemPlacement()
                                .clickable {
                                    viewmodel.selectYear(item as Int)
                                },
                            text = (item as Int).toString()
                        )
                    }

                    is SelectFuelType -> {
                        BrandSelectionRow(
                            modifier = Modifier
                                .padding(vertical = 8.dp)
                                .clickable {
                                    viewmodel.selectFuelType(item as FuelType)
                                },
                            text = (item as FuelType).name
                        )
                    }
                    else -> {}
                }

                if (index < items.size - 1)
                    HorizontalDivider(
                        color = backgroundDark
                    )
            }
        }
    }
}

@Composable
fun BrandSelectionRow(
    modifier: Modifier,
    text: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        CarlyDarkText(text = text, modifier = Modifier.weight(1f))
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
package com.raafat.carly.screens.carCreation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CarCreationScreen(
    viewmodel: CarCreationViewModel = hiltViewModel(),
    goBack: () -> Unit
) {
    BackHandler {
        if (viewmodel.prevState())
            goBack()
    }

    Column(modifier = Modifier) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 7.dp, vertical = 10.dp)
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
        }
    }
}
package com.raafat.carly.uitils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.raafat.carly.ui.theme.fontDark
import com.raafat.carly.ui.theme.fontLight
import com.raafat.carly.ui.theme.primaryColor


@Composable
fun CarlyDarkText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    textSize: TextUnit = 16.sp
) {
    Text(
        text = text,
        modifier = modifier,
        textAlign = textAlign,
        fontSize = textSize,
        color = fontDark
    )
}

@Composable
fun CarlyLightText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    textSize: TextUnit = 16.sp
) {
    Text(
        text = text,
        modifier = modifier,
        textAlign = textAlign,
        fontSize = textSize,
        color = fontLight
    )
}

@Composable
fun CarlyPrimaryText(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    textSize: TextUnit = 16.sp
) {
    Text(
        text = text,
        modifier = modifier,
        textAlign = textAlign,
        fontSize = textSize,
        color = primaryColor
    )
}
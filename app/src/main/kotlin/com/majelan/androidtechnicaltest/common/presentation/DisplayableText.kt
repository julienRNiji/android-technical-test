package com.majelan.androidtechnicaltest.common.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.majelan.androidtechnicaltest.ui.values.titleFontSize

@Composable
fun DisplayableText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = titleFontSize
        ),
        modifier = modifier
    )
}
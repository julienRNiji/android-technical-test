package com.majelan.androidtechnicaltest.common.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Loading(modifier: Modifier = Modifier) {
    DisplayableText(text = "Loading...", modifier = modifier)
}
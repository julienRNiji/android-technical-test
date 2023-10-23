package com.majelan.androidtechnicaltest.common.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Error(modifier: Modifier = Modifier) {
    DisplayableText(text = "Error...", modifier = modifier)
}
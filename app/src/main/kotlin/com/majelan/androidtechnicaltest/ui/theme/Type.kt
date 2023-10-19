package com.majelan.androidtechnicaltest.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.majelan.androidtechnicaltest.ui.values.mediumFontSize

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = mediumFontSize,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)
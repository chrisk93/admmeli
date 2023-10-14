package com.example.admissionmeli.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Yellow80 = Color(0xFFFFC107)
val YellowGrey80 = Color(0xFFFFEE58)
val Amber80 = Color(0xFFFFECB3)

val Yellow40 = Color(0xFFFFEE58)
val YellowGrey40 = Color(0xFFFFF9C4)
val Amber40 = Color(0xFFFFCA28)


val BrandColor = Color(0xFFFFF059)
val NavBarColor = Color(0xFFF7D415)
val primaryButtonColor = Color(0xFF3782FC)
val secondaryButtonColor = Color(0xFFE6ECfE)
val onSecondaryButtonColor = Color(0xFF557ED0)

val Color.Companion.PrimaryButton
    @Composable
    get() = primaryButtonColor

val Color.Companion.SecondaryButton
    @Composable
    get() = secondaryButtonColor

val Color.Companion.OnSecondaryButton
    @Composable
    get() = onSecondaryButtonColor

val ColorScheme.statusBarColor
    @Composable
    get() = if (!isSystemInDarkTheme()) NavBarColor else NavBarColor

val ColorScheme.brandColor
    @Composable
    get() = if (!isSystemInDarkTheme()) BrandColor else BrandColor
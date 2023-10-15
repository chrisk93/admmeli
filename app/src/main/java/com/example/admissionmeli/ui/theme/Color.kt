package com.example.admissionmeli.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


val Yellow80 = Color(0xFFFFC107)
val YellowGrey80 = Color(0xFFFAE204)
val Amber80 = Color(0xFFFFECB3)

val Yellow40 = Color(0xFFFAE204)
val YellowGrey40 = Color(0xFFFAE204)
val Amber40 = Color(0xFFFFCA28)

val md_theme_light_surface = Color(0xFFFFFBFF)
val md_theme_dark_surfaceVariant = Color(0xFF49473A)

val BrandColor = Color(0xFFFAE204)
val NavBarColor = Color(0xFFFAE204)
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

val ColorScheme.backgroundColor
    @Composable
    get() = if (!isSystemInDarkTheme()) md_theme_light_surface else md_theme_dark_surfaceVariant
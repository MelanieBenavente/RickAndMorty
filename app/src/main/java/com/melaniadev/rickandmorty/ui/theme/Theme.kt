package com.melaniadev.rickandmorty.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Color.Unspecified, secondary = Color.Unspecified, tertiary = Color.Unspecified
)

private val LightColorScheme = lightColorScheme(
    primary = Color.Unspecified, secondary = Color.Unspecified, tertiary = Color.Unspecified
)

@Composable
fun RickAndMortyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true, content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val customColors = if (!darkTheme) CustomColorsPalette() else CustomColorsPalette()
    CompositionLocalProvider(value = localCustomColorsPalette provides customColors) {
        MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
    }
}
package com.melaniadev.rickandmorty.ui.theme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class CustomColorsPalette {
    val orange = Color(color = 0xFFFFC107)
    val white = Color.White
    val black = Color.Black
    val light_green = Color(color = 0xFF83C538)
    val dark_green = Color(color = 0xFF4CAF50)
    val magenta = Color(color = 0xFF631E6F)
    val dark_magenta = Color(color = 0xFF4C1855)
    val red = Color.Red
    val blue_green = Color(color = 0xFF027167)
}


val localCustomColorsPalette = staticCompositionLocalOf { CustomColorsPalette() }

package com.melaniadev.rickandmorty.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.melaniadev.rickandmorty.R

val Typography = Typography(
    titleLarge =  TextStyle(
        fontFamily = FontFamily(Font(R.font.walter_turncoat)),
        fontWeight = FontWeight.Bold,
        fontSize = 50.sp,
        letterSpacing = (0).sp
    ),
    titleMedium =  TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Bold,
        lineHeight = 22.5.sp,
        fontSize = 30.sp,
        letterSpacing = (0).sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.ExtraBold,
        lineHeight = 24.sp,
        fontSize = 20.sp,
        letterSpacing = 0.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
        fontSize = 16.sp,
        letterSpacing = 0.sp
    )
)
package com.elyasasmad.my.prayertimes.presentation.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.wear.compose.material.Typography
import com.elyasasmad.my.prayertimes.R

val prayerTimesAppFont = FontFamily(
        Font(R.font.inter_bold, weight = FontWeight.Bold),
        Font(R.font.inter_semibold, weight = FontWeight.SemiBold),
        Font(R.font.inter_medium, weight = FontWeight.Medium),
        Font(R.font.inter_regular, weight = FontWeight.Normal),
        Font(R.font.inter_light, weight = FontWeight.Light),
)

// Set of Material typography styles to start with
val Typography = Typography(
//        body1 = TextStyle(
//                fontFamily = prayerTimesAppFont,
//                fontWeight = FontWeight.Normal,
//                fontSize = 16.sp
//        ),
        defaultFontFamily = prayerTimesAppFont
        /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
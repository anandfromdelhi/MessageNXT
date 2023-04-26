package com.example.messagenxt.utils

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

fun randomBrightColor(): Color {
    val hue = Random.nextFloat()
    val saturation = 0.9f
    val value = 0.9f
    return Color(android.graphics.Color.HSVToColor(floatArrayOf(hue * 360, saturation, value)))
}
package com.example.messagenxt.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun timeConverter(
    time: LocalDateTime
): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    return time.format(formatter)
}
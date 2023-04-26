package com.example.messagenxt.data

import com.example.messagenxt.utils.timeConverter
import java.time.LocalDateTime

data class Messages(
    val from:String ="",
    val to:String = "",
    val text:String = "",
    val time:String = timeConverter(LocalDateTime.now())
)


package com.example.messagenxt.data

import com.example.messagenxt.utils.timeConverter
import java.time.LocalDateTime

data class Chat(
    val from:String = "",
    val to:String = "",
    val message:String = "",
    val time:String = timeConverter(LocalDateTime.now())
)

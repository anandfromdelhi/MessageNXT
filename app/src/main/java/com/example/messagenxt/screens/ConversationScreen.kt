package com.example.messagenxt.screens

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.messagenxt.utils.composables.MessageFloatButton
import com.example.messagenxt.utils.composables.MessageNxtTopBar

@Composable
fun ConversationScreen(
    conversation: List<Messages>
) {
    Scaffold(
        topBar = { MessageNxtTopBar(navBackEnabled = true, title = "Conversation") },
        floatingActionButton = { MessageFloatButton(text = "", navBackEnabled = true) }
    ) { paddingValues ->
        paddingValues

    }
}
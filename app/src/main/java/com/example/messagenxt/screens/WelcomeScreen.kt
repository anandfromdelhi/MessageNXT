package com.example.messagenxt.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.example.messagenxt.utils.composables.MessageFloatButton
import com.example.messagenxt.utils.composables.MessageNxtTopBar
import com.example.messagenxt.utils.composables.MessagePreviewBox

@Composable
fun WelcomeScreen(
) {
    val messageList:List<Messages> = listOf(
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you kehfkdshlfkdshaflhlfdkhfadlkshflkashdlkhfdlkshfdslkhflkdsahflkjh"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are alkflsdhdlkfashflkdshflakhflkgsflkgflksdglkagflkadsglkfsglfdksagdlsfkglkyou"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi hokhfalfdhslkflkdsgflkdsgfldskgfdlksgfldksgflkgfdlskgdlkfsgdlfskgadlfskgkaldfsw are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you")
    )

    Scaffold(
        topBar = {
            MessageNxtTopBar(navBackEnabled = false, title = "MessageNXT")
        },
        floatingActionButton = {
            MessageFloatButton(text = "new message", navBackEnabled = false)
        }
    ) { paddingValues ->
        paddingValues

        LazyColumn {
            items(messageList){it ->
                MessagePreviewBox(message = it)
            }

        }


    }

}

data class Messages(
    val from: String,
    val time: String,
    val text: String
)




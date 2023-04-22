package com.example.messagenxt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.messagenxt.screens.ConversationScreen
import com.example.messagenxt.screens.Messages
import com.example.messagenxt.screens.WelcomeScreen
import com.example.messagenxt.ui.theme.MessageNXTTheme

class MainActivity : ComponentActivity() {

    val messageList:List<Messages> = listOf(
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Baba", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you kehfkdshlfkdshaflhlfdkhfadlkshflkashdlkhfdlkshfdslkhflkdsahflkjh"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Baba", "1:00", "Hi how are alkflsdhdlkfashflkdshflakhflkgsflkgflksdglkagflkadsglkfsglfdksagdlsfkglkyou"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Baba", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi hokhfalfdhslkflkdsgflkdsgfldskgfdlksgfldksgflkgfdlskgdlkfsgdlfskgadlfskgkaldfsw are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Baba", "1:00", "Hi how are you"),
        Messages("Baba", "1:00", "Hi how are you"),
        Messages("Johny", "1:00", "Hi how are you"),
        Messages("Baba", "1:00", "Hi how are you")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessageNXTTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ConversationScreen(conversation = messageList)
                }
            }
        }
    }
}

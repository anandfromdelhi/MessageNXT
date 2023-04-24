package com.example.messagenxt.screens


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.messagenxt.data.Chat
import com.example.messagenxt.data.UserData
import com.example.messagenxt.screens.viewModels.CRUDViewModel
import com.example.messagenxt.utils.alert
import com.example.messagenxt.utils.composables.MessageNxtTopBar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@Composable
fun ConversationScreen(
    crudViewModel: CRUDViewModel = CRUDViewModel(),
    userData: UserData?
) {
    val context = LocalContext.current
    var messages: List<Chat?> by remember { mutableStateOf(emptyList()) }
    val scope = rememberCoroutineScope()

    var message by remember { mutableStateOf("") }
    var to by remember {
        mutableStateOf("anand")
    }
    var from by remember {
        mutableStateOf(userData?.userName ?: "elon")
    }

    val db = Firebase.firestore
    val query = db.collection("Anand Kumar Mehta-anand")
    var data:List<Chat?> by remember { mutableStateOf(emptyList()) }
    val scrollState = rememberLazyListState()

    // access firestore at the time of app start to retrieve all the messages
    LaunchedEffect(key1 = true) {
        scope.launch {
            try {
                crudViewModel.retreiveData(
                    context = context,
                    from = "Anand Kumar Mehta",
                    to = "anand",
                ){list ->
                    messages = list

                }
            } catch (e: Exception) {
                alert(e.message ?: "error", context)
            }
            //scrolls to bottom when app launched
            scrollState.animateScrollToItem(messages.size - 1)
        }
    }

    //access firestore messages every time there is a change in data
    DisposableEffect(Unit) {
        val registration = query.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w("TAG", "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null && !snapshot.isEmpty) {
                messages = snapshot.documents.map { it.toObject(Chat::class.java) }

            } else {
                messages = emptyList()
            }
        }

        onDispose {
            registration.remove()
        }
    }




    Scaffold(
        topBar = { MessageNxtTopBar(navBackEnabled = true, title = "Conversation") }
    ) { paddingValues ->
        paddingValues

        Column() {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(messages) { message ->
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = if (message?.from == "Baba") {
                            Alignment.CenterEnd
                        } else {
                            Alignment.CenterStart
                        }
                    ) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = MaterialTheme.colors.onPrimary,
                            modifier = Modifier
                                .padding(10.dp)
                                .widthIn(max = 330.dp),
                            elevation = 5.dp

                        ) {

                            Column(
                                modifier = Modifier.padding(
                                    start = 20.dp,
                                    end = 20.dp,
                                    top = 10.dp,
                                    bottom = 10.dp,
                                )
                            ) {
                                if (message != null) {
                                    Text(
                                        text = message.message,
                                        style = MaterialTheme.typography.body1
                                    )
                                }
                                Spacer(modifier = Modifier.height(5.dp))

                                if (message != null) {
                                    Text(
                                        text = message.time,
                                        style = MaterialTheme.typography.caption,
                                        fontSize = 10.sp
                                    )
                                }


                            }
                        }

                    }
                }
            }
            Card(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(60.dp),
                backgroundColor = MaterialTheme.colors.secondary,
                shape = RoundedCornerShape(20.dp)
            ) {
                Row(
                    modifier = Modifier.padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        value = message, onValueChange = { message = it },
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.weight(1f),
                        maxLines = 5,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.White,
                            focusedBorderColor = MaterialTheme.colors.secondary
                        )
                    )
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable {
                                crudViewModel.saveData(chat = Chat(from, to, message), context)
                                message = ""

                                //scrolls to bottom
                                scope.launch { scrollState.animateScrollToItem(messages.size - 1) }
                            }
                    )
                }

            }
        }


    }
}
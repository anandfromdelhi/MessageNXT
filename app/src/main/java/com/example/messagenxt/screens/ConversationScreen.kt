package com.example.messagenxt.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.messagenxt.utils.composables.MessageNxtTopBar

@Composable
fun ConversationScreen(
    conversation: List<Messages>
) {
    var userMessage by remember { mutableStateOf("") }
    Scaffold(
        topBar = { MessageNxtTopBar(navBackEnabled = true, title = "Conversation") }
    ) { paddingValues ->
        paddingValues

        Column() {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(conversation) { message ->
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = if (message.from == "Baba") {
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
                                Text(
                                    text = message.text,
                                    style = MaterialTheme.typography.body1
                                )
                                Spacer(modifier = Modifier.height(5.dp))

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
                        value = userMessage, onValueChange = { userMessage = it },
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier.weight(1f),
                        maxLines = 5,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            backgroundColor = Color.White,
                            focusedBorderColor = MaterialTheme.colors.secondary
                        )
                    )
                    Icon(imageVector = Icons.Default.Send, contentDescription = null, modifier = Modifier.padding(10.dp))
                }

            }
        }


    }
}
package com.example.messagenxt.utils.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun MessagePreviewBox(message: com.example.messagenxt.data.Messages) {

    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 10.dp,
        modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp)
    ) {
        Column(
            modifier = Modifier.padding(
                start = 20.dp,
                end = 20.dp,
                top = 10.dp,
                bottom = 10.dp
            )
        ) {
            Row() {
                Text(text = message.from, style = MaterialTheme.typography.subtitle1)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = message.time, style = MaterialTheme.typography.subtitle1)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = message.text,
                style = MaterialTheme.typography.body1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
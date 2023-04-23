package com.example.messagenxt.utils.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MessageFloatButton(text:String,navBackEnabled:Boolean,onClick:()-> Unit = {}){
    FloatingActionButton(onClick = onClick, modifier = Modifier.width(170.dp)) {
        Row() {
            if (!navBackEnabled){ Text(text = text) }
            Spacer(modifier = Modifier.width(10.dp))
            Icon(imageVector = if (!navBackEnabled){ Icons.Default.Message }else{Icons.Default.Send}, contentDescription = null)

        }
    }
}
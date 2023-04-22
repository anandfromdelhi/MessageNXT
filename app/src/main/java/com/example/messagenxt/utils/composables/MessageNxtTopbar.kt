package com.example.messagenxt.utils.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MessageNxtTopBar(
    navBackEnabled:Boolean,
    title:String
){
    TopAppBar(
        modifier = Modifier
            .padding(10.dp)
            .clip(shape = RoundedCornerShape(20.dp))
    ) {
        Icon(
            imageVector = if (!navBackEnabled){ Icons.Default.Menu }else{Icons.Default.ArrowBack},
            contentDescription = null,
            modifier = Modifier.padding(10.dp)
        )
        Text(text = title, Modifier.weight(1f), textAlign = TextAlign.Center)
        Icon(
            imageVector = Icons.Default.Settings,
            modifier = Modifier.padding(10.dp),
            contentDescription = null
        )

    }
}
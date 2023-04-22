package com.example.messagenxt.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.messagenxt.R

@Composable
fun SignInScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.weight(8f), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.chat_logo),
                contentDescription = "chat logo",
                modifier = Modifier
                    .width(200.dp)
            )
        }
        Box(modifier = Modifier.weight(2f), contentAlignment = Alignment.Center) {
            OutlinedButton(onClick = { /*TODO*/ },
            shape = RoundedCornerShape(20.dp)
            ) {
                Row(modifier = Modifier.padding(5.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.google_logo),
                        contentDescription = "google logo",
                        modifier = Modifier.width(17.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Sign in with google")
                }
            }
        }

    }
}
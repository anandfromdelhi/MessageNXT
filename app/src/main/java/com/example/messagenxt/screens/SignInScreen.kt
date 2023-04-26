package com.example.messagenxt.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.messagenxt.R
import com.example.messagenxt.data.SignInState
import com.example.messagenxt.screens.viewModels.SignInViewModel
import com.example.messagenxt.utils.alert

@Composable
fun SignInScreen(
    signInState: SignInState,
    onSignInClick: () -> Unit,
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = signInState.signInError) {
        signInState.signInError?.let { error ->
            alert(message = error, context = context)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.weight(6f), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.chat_logo),
                contentDescription = "chat logo",
                modifier = Modifier
                    .width(200.dp)
            )
        }
        Box(modifier = Modifier.weight(2f), contentAlignment = Alignment.Center) {
            OutlinedButton(
                onClick = {
                    onSignInClick.invoke()
                },
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
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = "app developed by An@nD", fontSize = 20.sp, color = Color.LightGray, textAlign = TextAlign.Center)

    }
}
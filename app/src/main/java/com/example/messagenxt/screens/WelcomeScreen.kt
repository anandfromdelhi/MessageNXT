package com.example.messagenxt.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.messagenxt.Navigation.NavScreens
import com.example.messagenxt.data.User
import com.example.messagenxt.data.UserData
import com.example.messagenxt.screens.viewModels.CRUDViewModel
import com.example.messagenxt.utils.composables.MessageNxtTopBar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(
    userData: UserData?,
    onSignOut: () -> Unit,
    navController: NavController,
    crudViewModel: CRUDViewModel = CRUDViewModel()
) {

    val scope = rememberCoroutineScope()
    val user = Firebase.auth.currentUser
    val context = LocalContext.current
    var userList: List<User?> by remember { mutableStateOf(emptyList()) }

    LaunchedEffect(key1 = true) {
        scope.launch {

            crudViewModel.createUserInDatabase(
                User(
                    userName = user?.displayName.toString(),
                    userEmail = user?.email.toString(),
                    userPhoneNumber = user?.phoneNumber.toString()
                )
            )

            crudViewModel.getUsersList(context = context) { listOfUsers ->
                userList = listOfUsers
            }


        }
    }

    Scaffold(
        topBar = {
            MessageNxtTopBar(
                navBackEnabled = false,
                title = "Contacts",
                onSettingClick = onSignOut
            )

        }
    ) { paddingValues ->
        paddingValues

        LazyColumn {
            items(userList) { userItem ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(100.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = 5.dp
                ) {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                        .clickable {
                            navController.navigate(NavScreens.ConversationScreen.route)
                        }) {

                            userItem?.userName?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.h5
                                )
                            }
                            userItem?.userEmail?.let { Text(text = it) }

                    }
                }
            }
        }
    }
}





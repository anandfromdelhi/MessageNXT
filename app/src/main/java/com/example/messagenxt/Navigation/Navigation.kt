package com.example.messagenxt.Navigation

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.messagenxt.auth.GoogleAuthUiClient
import com.example.messagenxt.screens.ConversationScreen
import com.example.messagenxt.screens.SignInScreen
import com.example.messagenxt.screens.WelcomeScreen
import com.example.messagenxt.screens.viewModels.SignInViewModel
import com.example.messagenxt.utils.alert
import kotlinx.coroutines.launch

@Composable
fun Navigation(googleAuthUiClient: GoogleAuthUiClient, applicationContext: Context) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()


    NavHost(navController = navController, startDestination = NavScreens.SignInScreen.route) {

        composable(route = NavScreens.SignInScreen.route) {
            val viewModel = viewModel<SignInViewModel>()
            val state by viewModel.signInState.collectAsState()

            LaunchedEffect(key1 = Unit) {
                if (googleAuthUiClient.getSignedInUser() != null) {
                    navController.navigate(NavScreens.WelcomeScreen.route)
                }
            }

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == ComponentActivity.RESULT_OK) {
                        scope.launch {
                            val signInResult = googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            viewModel.signInResult(signInResult)

                        }
                    }

                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    alert(message = "Signed in successfully", context = applicationContext)
                    navController.navigate(NavScreens.WelcomeScreen.route)
                    viewModel.resetState()
                }
            }


            SignInScreen(
                signInState = state,
                onSignInClick = {
                    scope.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                }
            )

        }

        composable(route = NavScreens.WelcomeScreen.route) {
            WelcomeScreen(
                userData = googleAuthUiClient.getSignedInUser(),
                onSignOut = {
                    scope.launch {
                        googleAuthUiClient.signOut()
                        alert("Signed out", applicationContext)
                        navController.popBackStack()
                    }
                },
                navController = navController
            )
        }

        val routeToConversationScreen = NavScreens.ConversationScreen.route
        composable(route = "$routeToConversationScreen/{userName}",
            arguments = listOf(
                navArgument(name = "userName") { type = NavType.StringType }
            )
        ) { navBack ->
            val userName = navBack.arguments?.getString("userName")
            ConversationScreen(
                fromUserName = userName.toString(),
                navController = navController
            )
        }

    }
}

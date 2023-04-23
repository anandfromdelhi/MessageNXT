package com.example.messagenxt.Navigation

sealed class NavScreens (val route:String){
    object SignInScreen:NavScreens(route = "sign_in_screen")
    object WelcomeScreen:NavScreens(route = "welcome_screen")
    object ConversationScreen:NavScreens(route = "conversation_screen")
}
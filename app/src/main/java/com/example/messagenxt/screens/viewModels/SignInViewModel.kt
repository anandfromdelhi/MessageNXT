package com.example.messagenxt.screens.viewModels


import androidx.lifecycle.ViewModel
import com.example.messagenxt.data.SignInResult
import com.example.messagenxt.data.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel() : ViewModel() {
    private val _state = MutableStateFlow(SignInState())
    val signInState = _state.asStateFlow()

    fun signInResult(result: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    fun resetState() {
        _state.update {
            SignInState()
        }
    }
}
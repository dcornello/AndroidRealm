package com.dcornello.androidrealm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MarioLoginScreenViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(LoginScreenUIState(passwordIsVisible = false, email = "", password = ""))
    val uiState: StateFlow<LoginScreenUIState> = _uiState.asStateFlow()

    fun changeEmail(email: String) {
        _uiState.update {
            it.copy(email = email)
        }
    }

    fun passwordChange(password: String) {
        _uiState.update {
            it.copy(password = password)
        }
    }

    fun tooglePasswordVisibility() {
        _uiState.update {
            it.copy(passwordIsVisible = it.passwordIsVisible.not())
        }
    }
}
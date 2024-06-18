package com.dcornello.androidrealm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MarioLoginScreenViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginScreenUIState(passwordIsVisible = false, email = "", password = ""))
    val uiState: StateFlow<LoginScreenUIState> = _uiState.asStateFlow()

    private val _sideEffects = MutableSharedFlow<SideEffects>()
    val sideEffects: Flow<SideEffects> = _sideEffects.asSharedFlow()

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

    fun navigateToPage1() {
        sendSideEffect(SideEffects.NavigateToPage1(email = _uiState.value.email))
    }

    private fun sendSideEffect(sideEffects: SideEffects) {
        viewModelScope.launch {
            _sideEffects.emit(sideEffects)
        }
    }

    sealed class SideEffects {
        data class NavigateToPage1(val email: String) : SideEffects()

    }
}
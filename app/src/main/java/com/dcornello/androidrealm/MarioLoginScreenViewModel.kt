package com.dcornello.androidrealm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarioLoginScreenViewModel @Inject constructor(val analyticsService: AnalyticsService): ViewModel() {
    //@Inject -> funziona sono negli entry point, quindi uso constructor
    //lateinit var analyticsService: AnalyticsService

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
        analyticsService.trackHello()
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
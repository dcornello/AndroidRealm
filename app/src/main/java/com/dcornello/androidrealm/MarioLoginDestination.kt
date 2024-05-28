package com.dcornello.androidrealm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.collect

@Composable
fun MarioLoginDestination(navHostController: NavHostController, viewModel: MarioLoginScreenViewModel) {
    val stateStream by viewModel.uiState.collectAsState()
    LaunchedEffect(viewModel) {
        viewModel.sideEffects.onEach {sideEffect ->
            when(sideEffect) {
                is MarioLoginScreenViewModel.SideEffects.NavigateToPage1 -> navHostController.navigate("page1/${sideEffect.email}")
            }
        }.collect()
    }
    MarioLoginScreen(
        uiState = stateStream,
        onEmailChange = viewModel::changeEmail,
        onPasswordChange = viewModel::passwordChange,
        onLoginTap = viewModel::navigateToPage1,
        onTooglePasswordVisibilityTap = viewModel::tooglePasswordVisibility
    )
}
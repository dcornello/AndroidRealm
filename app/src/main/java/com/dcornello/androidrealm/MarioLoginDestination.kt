package com.dcornello.androidrealm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController

@Composable
fun MarioLoginDestination(navHostController: NavHostController, viewModel: MarioLoginScreenViewModel) {
    val stateStream by viewModel.uiState.collectAsState()

    MarioLoginScreen(
        uiState = stateStream,
        onEmailChange = viewModel::changeEmail,
        onPasswordChange = viewModel::passwordChange,
        onLoginTap = {
            navHostController.navigate("page1/${stateStream.email}")
        },
        onTooglePasswordVisibilityTap = viewModel::tooglePasswordVisibility
    )
}
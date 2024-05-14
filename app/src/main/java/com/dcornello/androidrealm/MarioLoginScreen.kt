package com.dcornello.androidrealm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dcornello.androidrealm.ui.theme.AndroidRealmTheme


data class LoginScreenUIState(
    val passwordIsVisible: Boolean,
    val email: String,
    val password: String
)

@Composable
fun MarioLoginScreen(
    uiState: LoginScreenUIState,
    onLoginTap: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTooglePasswordVisibilityTap: () -> Unit
) {
    Scaffold {
        Box(
            modifier = Modifier
                .padding(it)
                .background(color = MaterialTheme.colorScheme.onPrimary)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.align(Alignment.BottomCenter),
                verticalArrangement = Arrangement.spacedBy(space = 16.dp)
            ) {
                OutlinedTextField(
                    value = uiState.email,
                    onValueChange = onEmailChange,
                    shape = RoundedCornerShape(size = 16.dp)
                )

                OutlinedTextField(
                    visualTransformation = if (!uiState.passwordIsVisible) PasswordVisualTransformation() else VisualTransformation.None,
                    placeholder = { Text(text = "password") },
                    value = uiState.password,
                    onValueChange = onPasswordChange,
                    shape = RoundedCornerShape(size = 16.dp),
                    trailingIcon = {
                        IconButton(
                            onClick = onTooglePasswordVisibilityTap
                        ) {
                            if (uiState.passwordIsVisible) {
                                Icon(
                                    imageVector = Icons.Outlined.VisibilityOff,
                                    contentDescription = "hide password"
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Outlined.Visibility,
                                    contentDescription = "show password"
                                )
                            }
                        }
                    }
                )

                Button(onClick = onLoginTap) {
                    Text(text = "login")
                }
            }
        }
    }
}

@Composable
@Preview
fun MarioLoginScreenPreview() {
    AndroidRealmTheme {
        MarioLoginScreen(
            uiState = LoginScreenUIState(
                passwordIsVisible = false,
                email = "mario@a.com",
                password = "thepazz"
            ),
            onEmailChange = { },
            onPasswordChange = { },
            onLoginTap = { },
            onTooglePasswordVisibilityTap = { }
        )
    }
}
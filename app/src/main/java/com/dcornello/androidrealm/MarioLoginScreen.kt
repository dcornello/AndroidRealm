package com.dcornello.androidrealm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dcornello.androidrealm.ui.theme.AndroidRealmTheme


@Composable
fun MarioLoginScreen() {
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
                    value = "Email",
                    onValueChange = { },
                    shape = RoundedCornerShape(size = 16.dp)
                )

                var passwordIsVisible by remember {
                    mutableStateOf(value = false)
                }

                var password by remember {
                    mutableStateOf(value = "")
                }

                OutlinedTextField(
                    visualTransformation = if (!passwordIsVisible) PasswordVisualTransformation() else VisualTransformation.None,
                    placeholder = { Text(text = "password") },
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    shape = RoundedCornerShape(size = 16.dp),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                passwordIsVisible = !passwordIsVisible
                            }
                        ) {
                            if (passwordIsVisible) {
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
                    })
            }
        }
    }
}

@Composable
@Preview
fun MarioLoginScreenPreview() {
    AndroidRealmTheme {
        MarioLoginScreen()
    }
}
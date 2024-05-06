package com.dcornello.androidrealm

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dcornello.androidrealm.ui.theme.AndroidRealmTheme

@Composable
fun DiegoLoginScreen() {
    var emailValue by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }

    var passwordValue by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.imePadding(),
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .consumeWindowInsets(it)
                .systemBarsPadding()
                .fillMaxSize()
        ) {
            BackgroundWaveView(
                modifier = Modifier
                    .padding(top = 150.dp)
                    .fillMaxSize(),
                topWaveColor = MaterialTheme.colorScheme.secondary,
                bottomWaveColor = MaterialTheme.colorScheme.tertiary
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                Column {// Forum
                    Column(modifier = Modifier.padding(horizontal = 32.dp)) {

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text(text = "Email") },
                            value = emailValue,
                            onValueChange = { newValue ->
                                emailValue = newValue
                            },
                            shape = RoundedCornerShape(size = 16.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = MaterialTheme.colorScheme.primary,
                                focusedContainerColor = MaterialTheme.colorScheme.surface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
                                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
                            )
                        )
                        Text(
                            modifier = Modifier.padding(top = 2.dp),
                            text = if (emailError) "invalid email" else "",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.labelSmall,
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Column(modifier = Modifier.padding(horizontal = 32.dp)) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = { Text(text = "Password") },
                            value = passwordValue,
                            onValueChange = { newValue ->
                                passwordValue = newValue
                            },
                            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                IconButton(onClick = { showPassword = !showPassword }) {
                                    Icon(imageVector = if (showPassword) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff, contentDescription = "")
                                }
                            },
                            shape = RoundedCornerShape(size = 16.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = MaterialTheme.colorScheme.primary,
                                focusedContainerColor = MaterialTheme.colorScheme.surface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
                                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface,
                            )
                        )
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(
                                modifier = Modifier.padding(top = 2.dp),
                                text = if (passwordError) "Invalid password" else "",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                color = MaterialTheme.colorScheme.error,
                                style = MaterialTheme.typography.labelSmall,
                            )
                            ClickableText(
                                modifier = Modifier.padding(vertical = 8.dp),
                                text = buildAnnotatedString { append("Forget Password") },
                                style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onSecondary),
                                onClick = { /*TODO*/ }
                            )
                        }
                    }
                }
                Button(
                    modifier = Modifier.defaultMinSize(minWidth = 100.dp),
                    shape = RoundedCornerShape(size = 16.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "Log in")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(modifier = Modifier.width(80.dp))
                    Text(modifier = Modifier.padding(horizontal = 16.dp), text = "Or log in with", style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onPrimary))
                    Divider(modifier = Modifier.width(80.dp))
                }

                Row(
                    modifier = Modifier
                        .wrapContentSize(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FilledIconButton(
                        onClick = { /*TODO*/ },
                        content = { Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "") }
                    )
                    FilledIconButton(
                        onClick = { /*TODO*/ },
                        content = { Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "") }
                    )
                    FilledIconButton(
                        onClick = { /*TODO*/ },
                        content = { Icon(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "") }
                    )
                }
                Row(
                    modifier = Modifier
                        .wrapContentSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Not registered? ", style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onSecondary))
                    ClickableText(
                        modifier = Modifier.padding(vertical = 8.dp),
                        text = buildAnnotatedString { append("Sign up now") },
                        style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onPrimary),
                        onClick = { /*TODO*/ }
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(16.dp)
                )
            }
        }
    }
}

@Composable
fun BackgroundWaveView(
    modifier: Modifier = Modifier,
    topWaveColor: Color = Color.Black,
    bottomWaveColor: Color = Color.White
) {
    Box(modifier = modifier) {
        val screenWidthInPx = LocalConfiguration.current.screenWidthDp.dp.value * LocalDensity.current.density

        val x0 by remember { mutableFloatStateOf(0f) }
        val y0 by remember { mutableFloatStateOf(screenWidthInPx / 2) }

        val x1 by remember { mutableFloatStateOf(screenWidthInPx / 3f) }
        val y1 by remember { mutableFloatStateOf(600f) }
        val x2 by remember { mutableFloatStateOf(screenWidthInPx / 2f) }
        val y2 by remember { mutableFloatStateOf(0f) }
        val x3 by remember { mutableFloatStateOf(screenWidthInPx) }
        val y3 by remember { mutableFloatStateOf(200f) }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(bottomWaveColor)
        ) {
            Path().apply {
                reset()
                lineTo(x = x0, y = y0)
                cubicTo(x1 = x1, y1 = y1, x2 = x2, y2 = y2, x3 = x3, y3 = y3)
                lineTo(x = x3, y = 0f)

                drawPath(
                    color = topWaveColor,
                    path = this,
                )
            }
        }
    }
}

@Composable
@Preview
fun TestWavePreview() {
    AndroidRealmTheme {
        Surface {
            DiegoLoginScreen()
        }
    }
}
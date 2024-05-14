    package com.dcornello.androidrealm

    import android.os.Bundle
    import androidx.activity.ComponentActivity
    import androidx.activity.compose.setContent
    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.padding
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Surface
    import androidx.compose.material3.Text
    import androidx.compose.material3.TextField
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.collectAsState
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.tooling.preview.Preview
    import com.dcornello.androidrealm.ui.theme.AndroidRealmTheme
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.setValue
    import androidx.core.view.WindowCompat

    class MainActivity : ComponentActivity() {
        val loginScreenViewModel = MarioLoginScreenViewModel()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            WindowCompat.setDecorFitsSystemWindows(window, false)



            setContent {
                AndroidRealmTheme {

                    val stateStream by loginScreenViewModel.uiState.collectAsState()

                    MarioLoginScreen(
                        uiState = stateStream,
                        onEmailChange = loginScreenViewModel::changeEmail,
                        onPasswordChange = loginScreenViewModel::passwordChange,
                        onLoginTap = { },
                        onTooglePasswordVisibilityTap = loginScreenViewModel::tooglePasswordVisibility
                    )
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello word $name!",
            modifier = modifier
        )
    }

    @Composable
    fun LoginScreen() {

    // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

            Scaffold {
                Column (
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()
                        .background(Color.Red),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(text = "login")

                    var email by remember {
                        mutableStateOf(value = "")
                    }

                    TextField(value = email, onValueChange = {
                        email = it
                    })

                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun LoginScreenPreview() {
        AndroidRealmTheme {
            LoginScreen()
        }
    }
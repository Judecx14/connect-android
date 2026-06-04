package dev.fenix.customer.feature.auth.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.fenix.customer.feature.auth.login.component.AuthBy
import dev.fenix.customer.feature.auth.login.component.Form
import dev.fenix.customer.feature.auth.login.component.Greeting
import dev.fenix.customer.feature.auth.login.component.SignUpFooter
import dev.fenix.ui.theme.ConnectTheme
import dev.fenix.ui.modifier.ambient_glow.ambientGlow
import dev.fenix.ui.modifier.ambient_glow.model.Position

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit
) {
    val primary = ConnectTheme.colors.primary

    val formState by loginViewModel.formState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.ambientGlow(
            background = ConnectTheme.colors.background
        ) {
            spot(
                color = primary,
                x = Position.End,
                y = Position.Start,
                ratio = 0.8f,
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(ConnectTheme.dimensions.padding.normal)
        ) {
            Greeting(
                modifier = Modifier.weight(0.4f)
            )

            Form(
                state = formState,
                onEmailChange = loginViewModel::onEmailChange,
                onPasswordChange = loginViewModel::onPasswordChange,
                onSubmit = navigateToHome
            )

            AuthBy()

            SignUpFooter(modifier = Modifier.weight(0.1f)) { navigateToSignUp() }
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    ConnectTheme {
        LoginScreen(
            navigateToSignUp = { },
            navigateToHome = {}
        )
    }
}


package dev.fenix.customer.feature.auth.login

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.fenix.customer.common.ObserveFlowAsEvent
import dev.fenix.customer.feature.auth.login.component.AuthBy
import dev.fenix.customer.feature.auth.login.form.LoginFormSection
import dev.fenix.customer.feature.auth.login.form.LoginFormGroup
import dev.fenix.customer.feature.auth.login.component.Greeting
import dev.fenix.customer.feature.auth.login.component.SignUpFooter
import dev.fenix.ui.theme.ConnectTheme
import dev.fenix.ui.modifier.ambient_glow.ambientGlow
import dev.fenix.ui.modifier.ambient_glow.model.Position
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import dev.fenix.customer.common.asString

@Composable
private fun Content(
    uiState: LoginUiState,
    form: LoginFormGroup,
    snackbarHostState: SnackbarHostState,
    onSubmit: () -> Unit,
    navigateToSignUp: () -> Unit,
) {
    val colorPrimary = ConnectTheme.colors.primary

    Scaffold(
        modifier = Modifier.ambientGlow(
            background = ConnectTheme.colors.background
        ) {
            spot(
                color = colorPrimary,
                x = Position.End,
                y = Position.Start,
                ratio = 0.8f,
            )
        },
        containerColor = Color.Transparent,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .imePadding()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {
            Greeting()

            LoginFormSection(
                form = form,
                isLoading = uiState.isLoading,
                onSubmit = onSubmit
            )

            AuthBy()

            SignUpFooter { navigateToSignUp() }
        }
    }
}



@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit
) {
    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    ObserveFlowAsEvent(
        flow = loginViewModel.event,
    ) { event ->
        when (event) {
            is LoginUiEvent.Error -> {
                val errorMessage = event.reason.asString(context)
                scope.launch {
                    snackbarHostState.showSnackbar(message = errorMessage)
                }
            }

            is LoginUiEvent.Success -> navigateToHome()
        }
    }

    Content(
        uiState = uiState,
        form = loginViewModel.form,
        snackbarHostState = snackbarHostState,
        onSubmit = loginViewModel::onSubmit,
        navigateToSignUp = navigateToSignUp
    )

}

@Preview
@Composable
private fun LoginScreenPreview() {
    ConnectTheme {
        Content(
            uiState = LoginUiState(),
            form = LoginFormGroup(),
            snackbarHostState = SnackbarHostState(),
            onSubmit = { },
            navigateToSignUp = { }
        )
    }
}


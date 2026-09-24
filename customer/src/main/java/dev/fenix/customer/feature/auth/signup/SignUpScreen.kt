package dev.fenix.customer.feature.auth.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.fenix.customer.R
import dev.fenix.customer.common.ObserveFlowAsEvent
import dev.fenix.customer.feature.auth.signup.form.SignUpFormSection
import dev.fenix.customer.feature.auth.signup.component.Header
import dev.fenix.customer.feature.auth.signup.form.SignUpFormGroup
import dev.fenix.ui.component.app_bar.ConnectTopBar
import dev.fenix.ui.component.button.ConnectIconButton
import dev.fenix.ui.component.button.type.Variant
import dev.fenix.ui.component.icon.ConnectIcon
import dev.fenix.ui.component.icon.type.ConnectIcons
import dev.fenix.ui.theme.ConnectTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch
import dev.fenix.customer.common.asString

@Composable
private fun Content(
    uiState: SignUiState,
    form: SignUpFormGroup,
    snackbarHostState: SnackbarHostState,
    onSubmit: () -> Unit,
    navigateToBack: () -> Unit
) {
    Scaffold(
        topBar = {
            ConnectTopBar(
                title = stringResource(R.string.signup_screen_top_bar_title),
                leading = {
                    ConnectIconButton(
                        onClick = navigateToBack,
                        variant = Variant.Outlined
                    ) {
                        ConnectIcon(icon = ConnectIcons.ChevronLeft)
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .imePadding()
                .verticalScroll(rememberScrollState())
        ) {
            Header()
            SignUpFormSection(
                form = form,
                isLoading = uiState.isLoading,
                onSubmit = onSubmit
            )
        }
    }
}

@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = hiltViewModel(),
    navigateToBack: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val uiState by signUpViewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    ObserveFlowAsEvent(
        flow = signUpViewModel.event
    ) { event ->
        when (event) {
            is SignUpEvent.NavigateToHome -> navigateToHome()
            is SignUpEvent.Error -> {
                val errorMessage = event.reason.asString(context)
                scope.launch {
                    snackbarHostState.showSnackbar(message = errorMessage)
                }
            }
        }
    }

    Content(
        uiState = uiState,
        form = signUpViewModel.form,
        snackbarHostState = snackbarHostState,
        onSubmit = signUpViewModel::onSubmit,
        navigateToBack = navigateToBack
    )

}

@Preview
@Composable
private fun SignUpScreenPreview() {
    ConnectTheme {
        Content(
            uiState = SignUiState(),
            form = SignUpFormGroup(),
            snackbarHostState = SnackbarHostState(),
            navigateToBack = {},
            onSubmit = {}
        )
    }
}

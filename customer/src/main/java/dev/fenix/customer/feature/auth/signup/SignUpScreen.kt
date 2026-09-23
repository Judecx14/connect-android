package dev.fenix.customer.feature.auth.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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

@Composable
private fun Content(
    uiState: SignUiState,
    form: SignUpFormGroup,
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
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
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

    ObserveFlowAsEvent(
        flow = signUpViewModel.event
    ) { event ->
        when (event) {
            is SignUpEvent.NavigateToHome -> navigateToHome()
            is SignUpEvent.Error -> {
                event.reason
            }
        }
    }

    Content(
        uiState = uiState,
        form = signUpViewModel.form,
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
            navigateToBack = {},
            onSubmit = {}
        )
    }
}

package dev.fenix.customer.feature.auth.login.form

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.fenix.customer.R

sealed interface LoginFormControlError {
    data object Empty : LoginFormControlError
    data object InvalidEmail : LoginFormControlError
}

@Composable
fun LoginFormControlError.asString(): String {
    return when (this) {
        LoginFormControlError.Empty -> stringResource(R.string.login_screen_form_error_empty)
        LoginFormControlError.InvalidEmail -> stringResource(R.string.login_screen_form_error_invalid_email)
    }
}
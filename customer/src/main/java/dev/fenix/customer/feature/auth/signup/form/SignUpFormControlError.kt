package dev.fenix.customer.feature.auth.signup.form

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.fenix.customer.R

sealed interface SignUpFormControlError {
    data object Empty : SignUpFormControlError
    data object InvalidEmail : SignUpFormControlError
    data object PasswordTooShort : SignUpFormControlError
}

@Composable
fun SignUpFormControlError.asString(): String {
    return when (this) {
        SignUpFormControlError.Empty -> stringResource(R.string.signup_screen_form_error_empty)
        SignUpFormControlError.InvalidEmail -> stringResource(R.string.signup_screen_form_error_invalid_email)
        SignUpFormControlError.PasswordTooShort -> stringResource(R.string.signup_screen_form_error_password_short)
    }
}
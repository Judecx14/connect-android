package dev.fenix.customer.feature.auth.signup.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import dev.fenix.customer.R
import dev.fenix.ui.component.button.ConnectButton
import dev.fenix.ui.component.field.ConnectField
import dev.fenix.ui.theme.ConnectTheme

@Composable
fun Form(
    modifier: Modifier = Modifier,
    email: String,
    password: String,
    isLoading: Boolean = false,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit,
) {
    Column(
        modifier = modifier.padding(ConnectTheme.dimensions.padding.medium),
        verticalArrangement = Arrangement.spacedBy(ConnectTheme.dimensions.spacing.medium)
    ) {
        ConnectField(
            label = stringResource(R.string.signup_screen_form_field_label_email),
            placeholder = stringResource(R.string.signup_screen_form_field_placeholder_email),
            enabled = !isLoading,
            value = email,
            onChange = onEmailChange
        )

        ConnectField(
            label = stringResource(R.string.signup_screen_form_field_label_password),
            placeholder = stringResource(R.string.signup_screen_form_field_placeholder_password),
            enabled = !isLoading,
            value = password,
            onChange = onPasswordChange,
            visualTransformation = PasswordVisualTransformation()
        )

        ConnectButton(
            label = stringResource(R.string.signup_screen_form_button_signup),
            enabled = !isLoading,
            onClick = onSubmit,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HeaderPreview() {
    ConnectTheme {
        Form(
            email = "",
            password = "",
            onEmailChange = {},
            onPasswordChange = {},
            onSubmit = {}
        )
    }
}
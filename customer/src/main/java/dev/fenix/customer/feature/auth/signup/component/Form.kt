package dev.fenix.customer.feature.auth.signup.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import dev.fenix.customer.R
import dev.fenix.customer.feature.auth.signup.FormState
import dev.fenix.ui.component.button.ConnectButton
import dev.fenix.ui.component.field.ConnectField
import dev.fenix.ui.theme.ConnectTheme

@Composable
fun Form(
    modifier: Modifier = Modifier,
    state: FormState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit
) {
    Column(
        modifier = modifier.padding(ConnectTheme.dimensions.padding.medium),
        verticalArrangement = Arrangement.spacedBy(ConnectTheme.dimensions.spacing.medium)
    ) {
        ConnectField(
            label = stringResource(R.string.signup_screen_form_field_label_email),
            placeholder = stringResource(R.string.signup_screen_form_field_placeholder_email),
            value = state.email,
            onChange = onEmailChange
        )

        ConnectField(
            label = stringResource(R.string.signup_screen_form_field_label_password),
            placeholder = stringResource(R.string.signup_screen_form_field_placeholder_password),
            value = state.password,
            onChange = onPasswordChange,
            visualTransformation = PasswordVisualTransformation()
        )

        ConnectButton(
            label = stringResource(R.string.signup_screen_form_button_signup),
            onClick = onSubmit,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HeaderPreview() {
    ConnectTheme {
        Form(
            state = FormState(email = "", password = ""),
            onEmailChange = {},
            onPasswordChange = {},
            onSubmit = {}
        )
    }
}
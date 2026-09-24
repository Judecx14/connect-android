package dev.fenix.customer.feature.auth.login.form

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.foundation.layout.size

@Composable
fun LoginFormSection(
    modifier: Modifier = Modifier,
    form: LoginFormGroup,
    isLoading: Boolean = false,
    onSubmit: () -> Unit
) {

    Column(
        modifier = modifier.padding(ConnectTheme.dimensions.padding.medium),
        verticalArrangement = Arrangement.spacedBy(ConnectTheme.dimensions.spacing.medium)
    ) {
        ConnectField(
            value = form.email.value,
            onChange = { form.email.onChange(it) },
            label = stringResource(R.string.login_screen_form_field_label_email),
            placeholder = stringResource(R.string.login_screen_form_field_placeholder_email),
            enabled = !isLoading,
            hasError = form.email.error != null,
            errorMessage = form.email.error?.asString()
        )

        ConnectField(
            value = form.password.value,
            onChange = { form.password.onChange(it) },
            label = stringResource(R.string.login_screen_form_field_label_password),
            placeholder = stringResource(R.string.login_screen_form_field_placeholder_password),
            visualTransformation = PasswordVisualTransformation(),
            enabled = !isLoading,
            hasError = form.password.error != null,
            errorMessage = form.password.error?.asString()
        )

        ConnectButton(
            onClick = onSubmit,
            label = stringResource(R.string.login_screen_form_button_login),
            enabled = !isLoading,
            leading = if (isLoading) {
                { color ->
                    CircularProgressIndicator(
                        color = color,
                    )
                }
            } else null
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun LoginFormSectionPreview() {
    ConnectTheme {
        LoginFormSection(
            form = LoginFormGroup(),
            isLoading = true,
            onSubmit = {}
        )
    }
}
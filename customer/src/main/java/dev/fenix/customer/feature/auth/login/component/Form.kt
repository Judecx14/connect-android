package dev.fenix.customer.feature.auth.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import dev.fenix.customer.R
import dev.fenix.customer.feature.auth.login.FormState
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
            value = state.email,
            onChange = onEmailChange,
            label = stringResource(R.string.login_screen_form_field_label_email),
            placeholder = stringResource(R.string.login_screen_form_field_placeholder_email)
        )

        ConnectField(
            value = state.password,
            onChange = onPasswordChange,
            label = stringResource(R.string.login_screen_form_field_label_password),
            placeholder = stringResource(R.string.login_screen_form_field_placeholder_password),
            visualTransformation = PasswordVisualTransformation()
        )

        ConnectButton(
            onClick = onSubmit,
            label = stringResource(R.string.login_screen_form_button_login)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun AuthByPreview(){
    ConnectTheme {
        Form(
            state = FormState(
                email = "",
                password = ""
            ),
            onEmailChange = {},
            onPasswordChange = {},
            onSubmit = {}
        )
    }
}
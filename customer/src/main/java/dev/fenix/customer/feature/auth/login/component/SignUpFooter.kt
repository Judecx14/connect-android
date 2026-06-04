package dev.fenix.customer.feature.auth.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.fenix.customer.R
import dev.fenix.ui.component.button.ConnectButton
import dev.fenix.ui.component.button.type.Variant
import dev.fenix.ui.component.text.ConnectText
import dev.fenix.ui.theme.ConnectTheme

@Composable
fun SignUpFooter(
    modifier: Modifier = Modifier,
    navigateToSignUp: () -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ConnectText(text = stringResource(R.string.login_screen_form_signup_footer_question))
        ConnectButton(
            modifier = Modifier.width(ConnectTheme.dimensions.button.xl),
            label = stringResource(R.string.login_screen_form_signup_footer_button),
            onClick = { navigateToSignUp() },
            variant = Variant.Ghost
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthByPreview() {
    ConnectTheme {
        SignUpFooter { }
    }
}
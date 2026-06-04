package dev.fenix.customer.feature.auth.signup.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.fenix.customer.R
import dev.fenix.ui.component.text.ConnectText
import dev.fenix.ui.component.text.type.ConnectTextStyle
import dev.fenix.ui.theme.ConnectTheme

@Composable
fun Header(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(ConnectTheme.dimensions.padding.medium),
        verticalArrangement = Arrangement.spacedBy(ConnectTheme.dimensions.spacing.normal)
    ) {
        ConnectText(
            text = stringResource(R.string.signup_screen_header_title),
            style = ConnectTextStyle.Headline
        )
        ConnectText(text = stringResource(R.string.signup_screen_header_description))
    }
}

@Preview
@Composable
private fun HeaderPreview() {
    ConnectTheme {
        Header()
    }
}
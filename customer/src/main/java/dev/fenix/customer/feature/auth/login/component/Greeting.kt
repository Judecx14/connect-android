package dev.fenix.customer.feature.auth.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.fenix.customer.R
import dev.fenix.ui.component.tag.ConnectTag
import dev.fenix.ui.component.text.ConnectText
import dev.fenix.ui.component.text.type.ConnectTextStyle
import dev.fenix.ui.theme.ConnectTheme

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(
            start = ConnectTheme.dimensions.padding.medium,
            end = ConnectTheme.dimensions.padding.medium,
        ),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Row(
            modifier = Modifier.padding(bottom = ConnectTheme.dimensions.padding.normal),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                ConnectTheme.dimensions.spacing.small
            )
        ) {
            ConnectText(text = stringResource(R.string.login_screen_greeting_for))
            ConnectTag(
                text = stringResource(R.string.login_screen_greeting_tag)
            )
        }
        ConnectText(
            text = stringResource(R.string.login_screen_greeting_title),
            style = ConnectTextStyle.Display
        )
        ConnectText(text = stringResource(R.string.login_screen_greeting_description))
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthByPreview(){
    ConnectTheme {
        Greeting()
    }
}
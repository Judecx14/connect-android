package dev.fenix.customer.feature.auth.login.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.fenix.ui.component.button.ConnectIconButton
import dev.fenix.ui.component.button.type.Variant
import dev.fenix.ui.theme.ConnectTheme

@Composable
fun AuthBy(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ConnectIconButton(onClick = {}) {
            Text(text = "G")
        }

        Spacer(modifier.width(ConnectTheme.dimensions.spacing.small))

        ConnectIconButton(onClick = {}, variant = Variant.Outlined) {
            Text(text = "A")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthByPreview(){
    ConnectTheme {
        AuthBy()
    }
}

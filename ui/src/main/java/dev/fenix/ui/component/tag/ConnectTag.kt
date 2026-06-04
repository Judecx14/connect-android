package dev.fenix.ui.component.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import dev.fenix.ui.theme.ConnectTheme
import dev.fenix.ui.component.text.ConnectText

@Composable
fun ConnectTag(
    modifier: Modifier = Modifier,
    text: String,
    containerColor: Color = ConnectTheme.colors.primary,
    contentColor: Color = ConnectTheme.colors.onPrimary
) {
    Box(
        modifier = modifier
            .background(
                color = containerColor,
                shape = ConnectTheme.shapes.extraSmall
            )
            .padding(
                horizontal = ConnectTheme.dimensions.padding.small,
                vertical = ConnectTheme.dimensions.padding.xs
            )
    ) {
        ConnectText(
            text = text,
            color = contentColor,
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun ConnectTagPreview() {
    ConnectTheme {
        ConnectTag(text = "Example")
    }
}